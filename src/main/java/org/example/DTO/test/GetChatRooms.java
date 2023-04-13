package org.example.DTO.test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

public class GetChatRooms {
    public static void main(String[] args) {

        test test = new test();
        /*


        // Replace YOUR_API_KEY with your actual API key
        String apiKey = System.getenv("APIKEY");

        // Set the Riot API endpoint URL
        String url = "https://euw1.api.riotgames.com/lol/player-behavior/v1/chat-rooms?game=League+of+Legends&platform=EUW1";

        // Set the headers for the request
        String contentType = "application/json";
        String riotToken = apiKey;

        try {
            // Make the API request and store the response
            HttpResponse<JsonNode> response = Unirest.get(url)
                    .header("Content-Type", contentType)
                    .header("X-Riot-Token", riotToken)
                    .asJson();

            // Check if the request was successful
            if (response.getStatus() == 200) {
                // Print the list of chat rooms
                System.out.println("List of chat rooms:");
                response.getBody().getObject().getJSONArray("chatRooms").forEach(room -> {
                    System.out.println("Room ID: " + ((JSONObject)room).getString("chatRoomId"));
                    System.out.println("Room Name: " + ((JSONObject)room).getString("chatRoomName"));
                });
            } else {
                System.out.println("Error: " + response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
         */
    }
}