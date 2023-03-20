package org.example.DTO.models.v4;

import java.util.List;

public class CurrentGameInfo {
    long gameId;
    String gameType;
    long gameStartTime;
    long mapId;
    long gameLength;
    String platformId;
    String gameMode;
    List<BannedChampion> bannedChampions;
    long gameQueueConfigId;
    Observer observers;
    List<CurrentGameParticipant> participants;

    public CurrentGameInfo(long gameId, String gameType, long gameStartTime, long mapId, long gameLength, String platformId, String gameMode, List<BannedChampion> bannedChampions, long gameQueueConfigId, Observer observers, List<CurrentGameParticipant> participants) {
        this.gameId = gameId;
        this.gameType = gameType;
        this.gameStartTime = gameStartTime;
        this.mapId = mapId;
        this.gameLength = gameLength;
        this.platformId = platformId;
        this.gameMode = gameMode;
        this.bannedChampions = bannedChampions;
        this.gameQueueConfigId = gameQueueConfigId;
        this.observers = observers;
        this.participants = participants;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public List<BannedChampion> getBannedChampions() {
        return bannedChampions;
    }

    public void setBannedChampions(List<BannedChampion> bannedChampions) {
        this.bannedChampions = bannedChampions;
    }

    public long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    public void setGameQueueConfigId(long gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }

    public Observer getObservers() {
        return observers;
    }

    public void setObservers(Observer observers) {
        this.observers = observers;
    }

    public List<CurrentGameParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<CurrentGameParticipant> participants) {
        this.participants = participants;
    }
}
