package org.example.DTO.models.v5participants;

public class Participant {
    private String activePlatform;
    private String cid;
    private String game_name;
    private String game_tag;
    private boolean muted;
    private String name;
    private String pid;
    private String puuid;
    private String region;

    public String getActivePlatform() {
        return activePlatform;
    }

    public void setActivePlatform(String activePlatform) {
        this.activePlatform = activePlatform;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_tag() {
        return game_tag;
    }

    public void setGame_tag(String game_tag) {
        this.game_tag = game_tag;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}