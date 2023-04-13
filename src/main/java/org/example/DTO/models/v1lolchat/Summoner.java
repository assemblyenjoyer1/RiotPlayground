package org.example.DTO.models.v1lolchat;

public class Summoner {
    private String availability;
    private String gameName;
    private String gameTag;
    private int icon;
    private String id;
    private Long lastSeenOnlineTimestamp;
    private Lol lol;
    private String name;
    private Long obfuscatedSummonerId;
    private String patchline;
    private String pid;
    private String platformId;
    private String product;
    private String productName;
    private String puuid;
    private String statusMessage;
    private String summary;
    private Long summonerId;
    private Long time;

    public Summoner(String availability, String gameName, String gameTag, int icon, String id, Long lastSeenOnlineTimestamp,
                    Lol lol, String name, Long obfuscatedSummonerId, String patchline, String pid, String platformId,
                    String product, String productName, String puuid, String statusMessage, String summary,
                    Long summonerId, Long time) {
        this.availability = availability;
        this.gameName = gameName;
        this.gameTag = gameTag;
        this.icon = icon;
        this.id = id;
        this.lastSeenOnlineTimestamp = lastSeenOnlineTimestamp;
        this.lol = lol;
        this.name = name;
        this.obfuscatedSummonerId = obfuscatedSummonerId;
        this.patchline = patchline;
        this.pid = pid;
        this.platformId = platformId;
        this.product = product;
        this.productName = productName;
        this.puuid = puuid;
        this.statusMessage = statusMessage;
        this.summary = summary;
        this.summonerId = summonerId;
        this.time = time;
    }

    public String getAvailability() {
        return availability;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameTag() {
        return gameTag;
    }

    public int getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public Long getLastSeenOnlineTimestamp() {
        return lastSeenOnlineTimestamp;
    }

    public Lol getLol() {
        return lol;
    }

    public String getName() {
        return name;
    }

    public Long getObfuscatedSummonerId() {
        return obfuscatedSummonerId;
    }

    public String getPatchline() {
        return patchline;
    }

    public String getPid() {
        return pid;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getProduct() {
        return product;
    }

    public String getProductName() {
        return productName;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getSummary() {
        return summary;
    }

    public Long getSummonerId() {
        return summonerId;
    }

    public Long getTime() {
        return time;
    }
}
