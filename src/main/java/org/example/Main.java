package org.example;

import org.example.gui.LiveGameGUI;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello world!");
        LiveGameGUI gui = new LiveGameGUI();
        /*
        String name = "Thebausffs";
        Region region = Region.EUW1;

        PlayerService playerService = new PlayerService();
        MatchService matchService = new MatchService(playerService);
        DdragonService ddragonService = new DdragonService();
        ChatService chatService = new ChatService();
        System.out.println("Retrieving Summoner Matchhistory - ");
        String[] matchHistoryIDs = playerService.getSummonerData(name, region, MatchRegion.EUROPE);
        System.out.println("Retrieving match with id:" + matchHistoryIDs[0]);
        Match match = playerService.getMatchHistoryData(MatchRegion.EUROPE, matchHistoryIDs[0]);

        for(String x: match.getMetadata().getParticipants()){
            Summoner summoner = playerService.getSummonerNameByPuuid(x, region);
            System.out.println(summoner.getName());
        }

        for(Participant participant: match.getInfo().getParticipants()){
            System.out.println(participant.getSummonerName() + " - " + participant.getKills() + " / " + participant.getDeaths() + " / " + participant.getAssists());
        }

        System.out.println("Retrieving live game championData for: " + name);

        List<CurrentGameParticipant> participants = matchService.getCurrentGameParticipants(region, name);
        try{
            matchService.printGameParticipants(participants);
        }catch(SummonerNotIngameException e){
            System.out.println(e);
        }

        ddragonService.getDdragonData().data.get("Ashe");


        List<BannedChampion> bannedChampions = matchService.getBannedChampions(region, name);
        for(BannedChampion bannedChampion: bannedChampions){
            System.out.println(Transformer.getChampionNameById(bannedChampion.getChampionId()));
        }

        */


    }
}

        /*
        Summoner summoner = playerService.getSummonerByName();
        String summonerPuuid = summoner.getPuuid();
        System.out.println(summoner.getName());
        chatService.doSomething(summonerPuuid);
         */