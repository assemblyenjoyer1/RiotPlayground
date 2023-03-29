package org.example.services;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.DTO.models.enums.Region;
import org.example.DTO.models.v4league.LeagueEntry;
import org.example.DTO.models.v5Match.Match;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Set;

import static org.example.services.PlayerService.apiKey;
import static org.example.services.PlayerService.gson;

public class LeagueService {

    public Set<LeagueEntry> getLeagueEntryByEncryptedSummonerId(Region region, String encryptedSummonerId){
        HttpResponse<JsonNode> matchResponse;
        Set<LeagueEntry> leagueEntries = null;
        try{
            String matchUrl = "https://" + region + ".api.riotgames.com/lol/league/v4/entries/by-summoner/" + encryptedSummonerId + "?api_key=" + apiKey;
            System.out.println(matchUrl);
            matchResponse = Unirest.get(matchUrl).asJson();
            JSONArray leagueEntryArray = matchResponse.getBody().getArray();
            Type setType = new TypeToken<Set<LeagueEntry>>(){}.getType();
            leagueEntries = gson.fromJson(String.valueOf(leagueEntryArray), setType);
        }catch(UnirestException e){
            System.out.println("unable to get league entry for encrypted summoner id: " + encryptedSummonerId);
        }
        return leagueEntries;
    }

    public String calculateWinrate(int wins, int losses) {
        return String.format("%.2f%%", (double) wins / (wins + losses) * 100);
    }


}
