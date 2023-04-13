package org.example.DTO.test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.DTO.models.v4Summoner.Summoner;
import org.example.DTO.models.enums.Region;
import org.example.services.PlayerService;

public class GetSkins {

    public static void main(String[] args) {
        getSkins();
    }

    public static void getSkins(){
        // Replace YOUR_API_KEY with your actual API key
        String apiKey = System.getenv("APIKEY");

        PlayerService playerService = new PlayerService(apiKey);
        Summoner summoner = playerService.getSummonerByName(Region.EUW1, "Dawidsonek");
        try{
            // Make a GET request to the match history endpoint
            String summonerId = summoner.getAccountId();
            String matchHistoryUrl = "https://euw1.api.riotgames.com/lol/champions/v3/skins?ids=ALL&summonerId=" + summonerId + "&api_key=" + apiKey;
            HttpResponse<JsonNode> matchHistoryResponse = Unirest.get(matchHistoryUrl).asJson();
            System.out.println(matchHistoryResponse.getBody());
        }catch(UnirestException e){
            System.out.println("unable to get matchhistory response");
        }
    }

}
