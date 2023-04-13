package org.example.DTO.test.Ekko;

public class LeagueClient {
    private long pid;
    private ClientAuthInfo clientAuthInfo;
    private ProcessHandle process;

    public LeagueClient(long pid, ClientAuthInfo info, ProcessHandle process) {
        this.pid = pid;
        this.clientAuthInfo = info;
        this.process = process;
    }


    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public ClientAuthInfo getClientAuthInfo() {
        return clientAuthInfo;
    }

    public void setClientAuthInfo(ClientAuthInfo clientAuthInfo) {
        this.clientAuthInfo = clientAuthInfo;
    }

    public ProcessHandle getProcess() {
        return process;
    }

    public void setProcess(ProcessHandle process) {
        this.process = process;
    }
}