package org.example.DTO.test.Ekko;

public class ClientAuthInfo {
    private String riotClientAuthToken;
    private int riotClientPort;
    private String remotingAuthToken;
    private int remotingPort;

    public ClientAuthInfo(String riotClientAuthToken, int riotClientPort, String remotingAuthToken, int remotingPort) {
        this.riotClientAuthToken = riotClientAuthToken;
        this.riotClientPort = riotClientPort;
        this.remotingAuthToken = remotingAuthToken;
        this.remotingPort = remotingPort;
    }

    public ClientAuthInfo() {
    }

    public String getRiotClientAuthToken() {
        return riotClientAuthToken;
    }

    public void setRiotClientAuthToken(String riotClientAuthToken) {
        this.riotClientAuthToken = riotClientAuthToken;
    }

    public int getRiotClientPort() {
        return riotClientPort;
    }

    public void setRiotClientPort(int riotClientPort) {
        this.riotClientPort = riotClientPort;
    }

    public String getRemotingAuthToken() {
        return remotingAuthToken;
    }

    public void setRemotingAuthToken(String remotingAuthToken) {
        this.remotingAuthToken = remotingAuthToken;
    }

    public int getRemotingPort() {
        return remotingPort;
    }

    public void setRemotingPort(int remotingPort) {
        this.remotingPort = remotingPort;
    }
}
