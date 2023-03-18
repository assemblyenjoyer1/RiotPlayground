package org.example.playground;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.DTO.models.Match;
import org.example.DTO.models.MatchRegion;
import org.example.DTO.models.Region;
import org.example.DTO.models.Summoner;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetSummonerInfo {
    private static final String API_KEY = System.getenv("APIKEY");
    static Gson gson = new Gson();


    public String[] getSummonerData(String username, Region region, MatchRegion matchRegion) {
        String summonerName = username;
        String puuid = getSummonerPuuid(region, summonerName);
        HttpResponse<JsonNode> matchHistoryResponse = getMatchHistoryResponse(matchRegion, puuid);
        JSONArray matchHistoryArrayJson = matchHistoryResponse.getBody().getArray();
        System.out.println(matchHistoryArrayJson);
        String[] stringArray = gson.fromJson(String.valueOf(matchHistoryArrayJson), String[].class);

        return stringArray;
    }

    public Match getMatchHistoryData(MatchRegion matchRegion, String matchId){
        HttpResponse<JsonNode> matchResponse;
        Match match = null;
        try{
            String matchUrl = "https://" + matchRegion + ".api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + API_KEY;
            System.out.println(matchUrl);
            matchResponse = Unirest.get(matchUrl).asJson();
            JSONObject matchHistoryJSON = matchResponse.getBody().getObject();
            match = gson.fromJson(String.valueOf(matchHistoryJSON), Match.class);
        }catch(UnirestException e){
            System.out.println("unable to get matchhistory data");
        }
        return match;
    }

    public static HttpResponse<JsonNode> getMatchHistoryResponse(MatchRegion matchRegion, String puuid){
        HttpResponse<JsonNode> matchHistoryResponse = null;
        try{
            // Make a GET request to the match history endpoint
            String matchHistoryUrl = "https://" + matchRegion + ".api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?type=ranked&start=0&count=20&api_key=" + API_KEY;
            matchHistoryResponse = Unirest.get(matchHistoryUrl).asJson();
        }catch(UnirestException e){
            System.out.println("unable to get matchhistory response");

        }
        return matchHistoryResponse;
    }

    public static String getSummonerPuuid(Region region, String summonerName){
        String puuid = "";
        try{
            HttpResponse<JsonNode> summonerResponse;
            // Make a GET request to the summoner endpoint
            String summonerUrl = "https://" + region + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + API_KEY;
            summonerResponse = Unirest.get(summonerUrl).asJson();
            JSONObject summonerJson = summonerResponse.getBody().getObject();
            Summoner summoner = gson.fromJson(String.valueOf(summonerJson), Summoner.class);
            puuid = summoner.getPuuid();
        }catch(UnirestException e){
            System.out.println("unable to get summoner puuid");
        }
        return puuid;
    }

    public Summoner getSummonerNameByPuuid(String puuid, Region region){
        String summonerUrl = "https://" + region + ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key=" + API_KEY;
        try{
            HttpResponse<JsonNode> response = Unirest.get(summonerUrl).asJson();
            JSONObject summonerJson = response.getBody().getObject();
            Summoner summoner = gson.fromJson(String.valueOf(summonerJson), Summoner.class);
            return summoner;
        }catch(UnirestException e){
            System.out.println("unable to get summonername for puuid " + puuid);
        }
        return null;
    }
}
