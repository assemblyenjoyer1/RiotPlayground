package org.example.DTO.test.Ekko.platform;

public class LinuxPlatform extends PlatformBase {
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
        return String.format("-c \"ps -ww -fp %d\"", pid);
    }
}
