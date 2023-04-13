package org.example.DTO.models.v1rsoauth;

import com.google.gson.annotations.SerializedName;
import org.example.DTO.models.enums.Region;

import java.util.List;

public class UserInfo{
    public String availability;
    public String gameName;
    public String gameTag;
    public int icon;
    public String id;
    public Object lastSeenOnlineTimestamp;
    public Lol lol;
    public String name;
    public int obfuscatedSummonerId;
    public String patchline;
    public String pid;
    public String platformId;
    public String product;
    public String productName;
    public String puuid;
    public String statusMessage;
    public String summary;
    public int summonerId;
    public int time;

    public UserInfo(String availability, String gameName, String gameTag, int icon, String id, Object lastSeenOnlineTimestamp, Lol lol, String name, int obfuscatedSummonerId, String patchline, String pid, String platformId, String product, String productName, String puuid, String statusMessage, String summary, int summonerId, int time) {
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

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameTag() {
        return gameTag;
    }

    public void setGameTag(String gameTag) {
        this.gameTag = gameTag;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getLastSeenOnlineTimestamp() {
        return lastSeenOnlineTimestamp;
    }

    public void setLastSeenOnlineTimestamp(Object lastSeenOnlineTimestamp) {
        this.lastSeenOnlineTimestamp = lastSeenOnlineTimestamp;
    }

    public Lol getLol() {
        return lol;
    }

    public void setLol(Lol lol) {
        this.lol = lol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getObfuscatedSummonerId() {
        return obfuscatedSummonerId;
    }

    public void setObfuscatedSummonerId(int obfuscatedSummonerId) {
        this.obfuscatedSummonerId = obfuscatedSummonerId;
    }

    public String getPatchline() {
        return patchline;
    }

    public void setPatchline(String patchline) {
        this.patchline = patchline;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(int summonerId) {
        this.summonerId = summonerId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}