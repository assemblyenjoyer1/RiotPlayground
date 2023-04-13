package org.example.DTO.test;

import java.io.IOException;
import java.util.Scanner;

import okhttp3.*;

public class GetBlueEssence {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your League of Legends username:");
        String username = scanner.nextLine();

        System.out.println("Enter your League of Legends password:");
        String password = scanner.nextLine();

        System.out.println("Enter your League of Legends region (e.g. NA1):");
        String region = scanner.nextLine();

        String authToken = getAuthToken(username, password, region);
        int summonerId = 1;
        int blueEssence = getBlueEssence(authToken, region, summonerId);

        System.out.println("Your blue essence balance is: " + blueEssence);
    }

    private static String getAuthToken(String username, String password, String region) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create("{\"client_id\":\"riot-client\",\"nonce\":\"1\",\"redirect_uri\":\"https://127.0.0.1\",\"response_type\":\"token id_token\",\"scope\":\"openid lol ban lol openid email phone\",\"state\":\"2\",\"ui_locales\":\"en-US\"}", mediaType);
        Request request = new Request.Builder()
                .url("https://auth.riotgames.com/api/v1/authorization")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                .addHeader("Pragma", "no-cache")
                .addHeader("Accept", "*/*")
                .addHeader("X-Riot-ClientPlatform", "pc")
                .addHeader("X-Riot-ClientRegion", region)
                .build();

        Response response = client.newCall(request).execute();
        String location = response.header("location");
        String[] parts = location.split("#access_token=");
        System.out.println(parts[1]);
        String authToken = parts[1];

        return authToken;
    }

    private static int getBlueEssence(String authToken, String region, int summonerId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("https://%s.api.riotgames.com/lol/platform/v3/runes/by-summoner/%s", region, summonerId))
                .addHeader("Authorization", "Bearer " + authToken)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        int blueEssence = Integer.parseInt(responseBody);

        return blueEssence;
    }
}
