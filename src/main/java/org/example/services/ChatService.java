package org.example.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class ChatService {

    private static final String API_KEY = System.getenv("APIKEY");

    public void doSomething(String puuid){
        HttpResponse<JsonNode> response;
        try{
            String url = "https://eun1.api.riotgames.com/lol/chat/v1/conversations/" + puuid + "?api_key=" + API_KEY;
            System.out.println(url);
            response = Unirest.get(url).asJson();
            JSONObject matchHistoryJSON = response.getBody().getObject();
        }catch(UnirestException e){
            System.out.println("unable to get matchhistory root");
        }
    }
}
