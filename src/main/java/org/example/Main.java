package org.example;

import org.example.DTO.models.*;
import org.example.DTO.models.ddragon.Champion;
import org.example.DTO.models.ddragon.ChampionData;
import org.example.DTO.models.v4.BannedChampion;
import org.example.DTO.models.v4.CurrentGameParticipant;
import org.example.DTO.models.v4.SummonerService;
import org.example.DTO.test.Printer;
import org.example.DTO.test.Transformer;
import org.example.playground.DdragonService;
import org.example.playground.MatchService;
import org.example.playground.PlayerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        PlayerService playerService = new PlayerService();
        MatchService matchService = new MatchService(playerService);
        DdragonService ddragonService = new DdragonService();

        System.out.println("Retrieving Summoner Matchhistory - ");
        String[] matchHistoryIDs = playerService.getSummonerData("Freedomfighter28", Region.EUW1, MatchRegion.EUROPE);
        System.out.println("Retrieving match with id:" + matchHistoryIDs[0]);
        Match match = playerService.getMatchHistoryData(MatchRegion.EUROPE, matchHistoryIDs[0]);

        for(String x: match.getMetadata().getParticipants()){
            Summoner summoner = playerService.getSummonerNameByPuuid(x, Region.EUW1);
            System.out.println(summoner.getName());
        }

        for(Participant participant: match.getInfo().getParticipants()){
            System.out.println(participant.getSummonerName() + " - " + participant.getKills() + " / " + participant.getDeaths() + " / " + participant.getAssists());
        }

        System.out.println("Retrieving live game championData for: Freedomfighter28");

        List<CurrentGameParticipant> participants = matchService.getCurrentGameParticipants(Region.EUW1, "Freedomfighter28");
        List<BannedChampion> bannedChampions = matchService.getBannedChampions(Region.EUW1, "Freedomfighter28");
        matchService.printGameParticipants(participants);

    }
}