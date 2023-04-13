package org.example.DTO.test.Ekko.platform;

public class WindowsPlatform extends PlatformBase {

    @Override
    protected String getFileName() {
        return "powershell.exe";
    }

    @Override
    protected String getAuthTokenRegex() {
        return "--(riotclient|remoting)-auth-token=(.*?)( --|\n|$|\")";
    }

    @Override
    protected String getPortRegex() {
        return "--(riotclient-|)app-port=(.*?)( --|\n|$|\")";
    }

    @Override
    protected String getCommand(long pid) {
        return String.format("WMIC PROCESS WHERE ProcessId=%d get commandline", pid);
    }
}
