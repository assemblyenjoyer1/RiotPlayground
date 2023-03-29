package org.example.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.DTO.models.enums.Region;
import org.example.DTO.models.v4league.LeagueEntry;
import org.example.DTO.models.v5Match.Match;
import org.json.JSONObject;

import static org.example.services.PlayerService.apiKey;
import static org.example.services.PlayerService.gson;

public class LeagueService {

    public LeagueEntry getLeagueEntryByEncryptedSummonerId(Region region, String encryptedSummonerId){
        HttpResponse<JsonNode> matchResponse;
        LeagueEntry leagueEntry = null;
        try{
            String matchUrl = "https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key=" + apiKey;
            System.out.println(matchUrl);
            matchResponse = Unirest.get(matchUrl).asJson();
            JSONObject matchHistoryJSON = matchResponse.getBody().getObject();
            leagueEntry = gson.fromJson(String.valueOf(matchHistoryJSON), LeagueEntry.class);
        }catch(UnirestException e){
            System.out.println("unable to get league entry for encrypted summoner id: " + encryptedSummonerId);
        }
        return leagueEntry;
    }

}
