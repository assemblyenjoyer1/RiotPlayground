package org.example;

import org.example.DTO.models.*;
import org.example.DTO.models.ddragon.Champion;
import org.example.DTO.models.ddragon.ChampionData;
import org.example.DTO.models.v4.BannedChampion;
import org.example.DTO.models.v4.CurrentGameInfo;
import org.example.DTO.models.v4.CurrentGameParticipant;
import org.example.DTO.test.Transformer;
import org.example.playground.DdragonService;
import org.example.playground.GetSummonerInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");
        GetSummonerInfo getSummonerInfo = new GetSummonerInfo();
        DdragonService ddragonService = new DdragonService();
        ChampionData championData = ddragonService.getDdragonData();

        System.out.println("Retrieving Summoner Matchhistory - ");
        String[] matchHistoryIDs = getSummonerInfo.getSummonerData("FREEDOMFIGHTER28", Region.EUW1, MatchRegion.EUROPE);
        System.out.println("Retrieving match with id:" + matchHistoryIDs[0]);
        Match match = getSummonerInfo.getMatchHistoryData(MatchRegion.EUROPE, matchHistoryIDs[0]);

        for(String x: match.getMetadata().getParticipants()){
            Summoner summoner = getSummonerInfo.getSummonerNameByPuuid(x,Region.EUW1);
            System.out.println(summoner.getName());
        }

        for(Participant participant: match.getInfo().getParticipants()){
            System.out.println(participant.getSummonerName() + " - " + participant.getKills() + " / " + participant.getDeaths() + " / " + participant.getAssists());
        }

        System.out.println("Retrieving live game championData for: Freedomfighter28");
        Summoner summoner = getSummonerInfo.getSummonerByName(Region.EUW1, "Freedomfighter28");
        CurrentGameInfo currentGameInfo= getSummonerInfo.getCurrentGameInfo(Region.EUW1, summoner.getId());
        List<CurrentGameParticipant> participants = currentGameInfo.getParticipants();
        for(CurrentGameParticipant p: participants){
            System.out.println(p.getSummonerName());
        }

        Map<String, String> championNameById = new HashMap<>();
        for (Champion champion : championData.data.values()) {
            championNameById.put(String.valueOf(champion.id), champion.name);
        }

        List<BannedChampion> champions = currentGameInfo.getBannedChampions();
        for(BannedChampion champion: champions){
            String name = Transformer.getChampionNameById(champion.getChampionId());
            System.out.println(name);
        }

        List<CurrentGameParticipant> participantsList = currentGameInfo.getParticipants();
        for(CurrentGameParticipant currentGameParticipant: participantsList){
            System.out.println(currentGameParticipant.getSummonerName() + " is playing: " + Transformer.getChampionNameById(currentGameParticipant.getChampionId()));
            System.out.println();
        }

    }
}