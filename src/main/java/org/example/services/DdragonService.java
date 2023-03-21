package org.example.playground;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.DTO.models.ddragon.ChampionData;
import org.json.JSONObject;

public class DdragonService {

    public ChampionData getDdragonData(){
        Gson gson = new Gson();
        String summonerUrl = "http://ddragon.leagueoflegends.com/cdn/13.5.1/data/en_US/champion.json";
        try{
            HttpResponse<JsonNode> response = Unirest.get(summonerUrl).asJson();
            JSONObject summonerJson = response.getBody().getObject();
            ChampionData championData = gson.fromJson(String.valueOf(summonerJson), ChampionData.class);
            return championData;
        }catch(UnirestException e){
            System.out.println("unable to get champion data");
        }
        return null;
    }

}
