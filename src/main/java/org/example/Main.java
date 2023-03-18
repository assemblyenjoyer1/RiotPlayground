package org.example;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.example.DTO.models.*;
import org.example.playground.GetSummonerInfo;

public class Main {
    public static void main(String[] args) throws UnirestException {
        System.out.println("Hello world!");
        GetSummonerInfo getSummonerInfo = new GetSummonerInfo();
        System.out.println("Retrieving Summoner Matchhistory");
        String[] matchHistoryIDs = getSummonerInfo.getSummonerData("Dawidsonek", Region.EUW1, MatchRegion.EUROPE);
        System.out.println("Retrieving match with id:" + matchHistoryIDs[0]);
        Match match = getSummonerInfo.getMatchHistoryData(MatchRegion.EUROPE, matchHistoryIDs[0]);

        for(String x: match.getMetadata().getParticipants()){
            Summoner summoner = getSummonerInfo.getSummonerNameByPuuid(x,Region.EUW1);
            System.out.println(summoner.getName());
        }

        match.getInfo().getParticipants().get(0).getKills();
        for(Participant participant: match.getInfo().getParticipants()){
            System.out.println(participant.getSummonerName() + " - " + participant.getKills() + " / " + participant.getDeaths() + " / " + participant.getAssists());
        }
    }
}