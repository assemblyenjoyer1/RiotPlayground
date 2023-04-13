package org.example.DTO.test.Ekko.platform;

public class OsxPlatform extends PlatformBase {

    @Override
    protected String getFileName() {
        return "/bin/bash";
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
        return String.format("ps -ww -fp %d", pid);
    }
}



