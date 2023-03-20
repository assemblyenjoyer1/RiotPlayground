package org.example.playground;

import org.example.DTO.models.Region;
import org.example.DTO.models.Summoner;
import org.example.DTO.models.v4.BannedChampion;
import org.example.DTO.models.v4.SummonerService;
import org.example.DTO.models.v4.CurrentGameParticipant;
import org.example.DTO.test.Transformer;

import java.util.List;

public class MatchService {

    PlayerService playerService;
    SummonerService summonerService;

    public MatchService(PlayerService playerService){
        this.playerService = playerService;
    }

    public List<CurrentGameParticipant> getCurrentGameParticipants(Region region, String name){
        Summoner summoner = playerService.getSummonerByName(region, name);
        this.summonerService = playerService.getCurrentGameInfo(region, summoner.getId());
        List<CurrentGameParticipant> participants = summonerService.getParticipants();
        if(participants != null){
            return participants;
        }
        return null;
    }

    public List<BannedChampion> getBannedChampions(Region region, String name){
        Summoner summoner = playerService.getSummonerByName(Region.EUW1, name);
        List<BannedChampion> champions = summonerService.getBannedChampions();
        if (champions != null){
            return champions;
        }
        return null;
    }

    public void printGameParticipants(List<CurrentGameParticipant> currentGameParticipants){
        for(CurrentGameParticipant participant: currentGameParticipants){
            System.out.println(participant.getSummonerName() + " is currently playing " + Transformer.getChampionNameById(participant.getChampionId()));
        }
    }

}
