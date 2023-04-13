package org.example.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.example.utils.models.PunishmentType;

public class ReportService {

    //REPORT PlAYERS MANUALLY AFTER A GAME:
    //https://na1.api.riotgames.com/lol/player-behavior/v1/punishments/player
    // MAY BE A VIOLATION OF RIOTS TOS

    public void reportPlayer(String encryptedAccountId, PunishmentType punishmentType){
        // Replace YOUR_API_KEY with your actual API key
        String apiKey = System.getenv("APIKEY");

        // Set the Riot API endpoint URL
        String url = "https://euw1.api.riotgames.com/lol/player-behavior/v1/punishments/player";

        // Set the headers for the request
        String contentType = "application/json";
        String riotToken = apiKey;

        // Set the payload for the request
        String body = String.format("{\"accountId\": \"%s\", \"type\": \"%s\"}", encryptedAccountId, punishmentType);

        try {
            // Make the API request and store the response
            HttpResponse<JsonNode> response = Unirest.post(url)
                    .header("Content-Type", contentType)
                    .header("X-Riot-Token", riotToken)
                    .body(body)
                    .asJson();

            // Check if the request was successful
            if (response.getStatus() == 204) {
                System.out.println("Player successfully reported for " + punishmentType);
            } else {
                System.out.println("Error: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
