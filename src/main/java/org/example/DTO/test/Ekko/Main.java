package org.example.DTO.test.Ekko;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpMethod;
import org.example.DTO.models.v1lolchat.Summoner;
import org.example.DTO.models.v1rsoauth.UserInfo;
import org.example.DTO.models.v5participants.ApiResult;
import org.example.DTO.models.v5participants.Participant;
import org.example.DTO.test.Transformer;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {
        LeagueClientWatcher watcher = new LeagueClientWatcher();
        Gson gson = new Gson();
        watcher.observe();
        ClientAuthInfo info = watcher.getAuthInfo();

        LeagueApi remotingApi = new LeagueApi(info.getRemotingAuthToken(),info.getRemotingPort());
        LeagueApi clientApi = new LeagueApi(info.getRiotClientAuthToken(), info.getRiotClientPort());
        //CompletableFuture<String> future = api.sendAsync(HttpMethod.GET, "/lol-summoner/v1/current-summoner", null);
        //String result = future.join();
        //System.out.println("Res:" + result);


        printSummonersInLobby(clientApi,remotingApi, gson);
        //acceptQueue(remotingApi);
        //startQueue(remotingApi);
        //getAllNames(remotingApi);
        //getWallet(remotingApi);

/*
        CompletableFuture<String> content3 = remotingApi.sendAsync(HttpMethod.GET, "/lol-chat/v1/me", null);
        String content3String = content3.join();
        System.out.println(content3String);
        Summoner summoner = gson.fromJson(String.valueOf(content3String), Summoner.class);
        System.out.println(summoner.getGameTag());

 */

        /*
        CompletableFuture<String> content2 = clientApi.sendAsync(HttpMethod.GET, "/rso-auth/v1/authorization/userinfo", null);
        String content2String = content2.join();
        System.out.println(content2String);

        CompletableFuture<String> content4 = api.sendAsync(HttpMethod.GET, "/lol-chat/v1/conversations", null);
        String content4String = content4.join();
        System.out.println(content4String);


        CompletableFuture<String> content5 = api.sendAsync(HttpMethod.GET, "/lol-champ-select/v1/session", null);
        String content5String = content5.join();
        System.out.println(content5String);
        */

    }


    public static String extractRegionId(LeagueApi api) {
        CompletableFuture<String> content2 = api.sendAsync(HttpMethod.GET, "/rso-auth/v1/authorization/userinfo", null);
        String jsonString = content2.join();
        String regionId = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Object userInfo = jsonObject.get("userInfo");
            JSONObject userInfoObject;
            if (userInfo instanceof String) {
                userInfoObject = new JSONObject((String) userInfo);
            } else {
                userInfoObject = (JSONObject) userInfo;
            }
            JSONObject lolObject = userInfoObject.getJSONObject("lol");
            if (lolObject.has("cpid")) {
                regionId = lolObject.getString("cpid");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return regionId;
    }


    //rso-auth/v1/authorization/userinfo
    /**
     * Method to get all summoners in the lobby censored or not using the
     * "/chat/v5/participants/champ-select" endpoint from the LCU api
     * REQUIRES: Client auth token + port
     */
    public static void printSummonersInLobby(LeagueApi api, LeagueApi apiRegion, Gson gson){
        CompletableFuture<String> content = api.sendAsync(HttpMethod.GET, "/chat/v5/participants/champ-select", null);
        String content2 = content.join();
        ApiResult apiResult = gson.fromJson(String.valueOf(content2), ApiResult.class);
        List<Participant> participants = apiResult.getParticipants();
        String region = Transformer.getRegionString(extractRegionId(api));
        System.out.println("REGION:" + region);
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.op.gg/multisearch/" + region +"?summoners=");
        for(Participant participant: participants) {
            sb.append(URLEncoder.encode(participant.getName(), StandardCharsets.UTF_8) + ",");
        }
        sb.setLength(sb.length() - 1);
        String opggLink = sb.toString();
        try{
            Desktop.getDesktop().browse(new URI(opggLink));
        }catch (URISyntaxException | IOException e){
            System.out.println(e);
        }
        System.out.println(opggLink);
    }

    /**
     * Method to accept a pending queue in league of legends using the
     * "/lol-matchmaking/v1/ready-check/accept" endpoint from the LCU api
     * REQUIRES: Remoting auth token + port
     */
    public static void acceptQueue(LeagueApi api){
        api.sendAsync(HttpMethod.POST, "/lol-matchmaking/v1/ready-check/accept", null);
    }

    public static void getWallet(LeagueApi api){
        CompletableFuture<String> content = api.sendAsync(HttpMethod.GET, "/lol-inventory/v1/wallet", null);
        String contentString = content.join();
        System.out.println("RES:" + contentString);
    }
    /**
     * Method to start a queue in league of legends using the
     * "/lol-lobby/v2/lobby/matchmaking/search" endpoint from the LCU api
     * REQUIRES: Remoting auth token + port
     */
    public static void startQueue(LeagueApi api){
        CompletableFuture<String> content = api.sendAsync(HttpMethod.POST, "/lol-lobby/v2/lobby/matchmaking/search", null);
        String contentString = content.join();
        System.out.println("RES:" + contentString);
    }

    public static void getAllNames(LeagueApi api){
        CompletableFuture<String> content = api.sendAsync(HttpMethod.GET, "/lol-champ-select/v1/session", null);
        String contentString = content.join();
        System.out.println("RES:" + contentString);
    }

    public static String encryptSummonerId(long summonerId) {
        String key = "urkey"; // Replace with your API key
        String idStr = Long.toString(summonerId);
        String message = idStr + ":0:" + System.currentTimeMillis();
        String encrypted = "";
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encrypted = new String(cipher.doFinal(message.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }


}
