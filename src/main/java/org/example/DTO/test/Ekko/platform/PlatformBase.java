package org.example.DTO.test.Ekko.platform;

import org.example.DTO.test.Ekko.ClientAuthInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PlatformBase {
    protected abstract String getFileName();
    protected abstract String getAuthTokenRegex();
    protected abstract String getPortRegex();
    protected abstract String getCommand(long pid);

    public ClientAuthInfo extractArguments(long pid) {
        String command = getCommand(pid);
        String commandLine = getCommandLine(getFileName(), command);
        return (commandLine == null || commandLine.isEmpty()) ? null : extractArguments(commandLine);
    }

    public ClientAuthInfo extractArguments(String commandLine) {
        String token = getAuthToken(commandLine);
        String riotClientAppPort = getRiotClientAppPort(commandLine);
        String port = getAppPort(commandLine);
        String remotingAuthToken = getRemotingAuthToken(commandLine);

        ClientAuthInfo clientAuthInfo = new ClientAuthInfo(token,Integer.valueOf(riotClientAppPort),remotingAuthToken, Integer.valueOf(port));
        return clientAuthInfo;
    }

    private String getAuthToken(String commandLine){
        Pattern pattern = Pattern.compile("--riotclient-auth-token=(\\S+)");
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.find()) {
            String authToken = matcher.group(1);
            authToken = authToken.substring(0, authToken.length()-1);
            return authToken;
        } else {
            System.out.println("authToken not found");
        }
        return null;
    }

    public String getRiotClientAppPort(String commandLine){
        Pattern pattern = Pattern.compile("--riotclient-app-port=(\\S+)");
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.find()) {
            String appPort = matcher.group(1);
            appPort = appPort.substring(0, appPort.length()-1);
            return appPort;
        } else {
            System.out.println("appPort not found");
        }
        return null;
    }

    public String getRemotingAuthToken(String commandLine){
        Pattern pattern = Pattern.compile("--remoting-auth-token=(\\S+)");
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.find()) {
            String remotingAuthToken = matcher.group(1);
            remotingAuthToken = remotingAuthToken.substring(0, remotingAuthToken.length()-1);
            return remotingAuthToken;
        } else {
            System.out.println("RiotClientAppPort not found");
        }
        return null;
    }

    public String getAppPort(String commandLine){
        Pattern pattern = Pattern.compile("--app-port=(\\S+)");
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.find()) {
            String appPort = matcher.group(1);
            appPort = appPort.substring(0, appPort.length()-1);
            return appPort;
        } else {
            System.out.println("appPort not found");
        }
        return null;
    }

    public String getRegion(String commandLine){
        Pattern pattern = Pattern.compile("--region=(\\S+)");
        Matcher matcher = pattern.matcher(commandLine);
        if (matcher.find()) {
            String region = matcher.group(1);
            region = region.substring(0, region.length()-1);
            return region;
        } else {
            System.out.println("region not found");
        }
        return null;
    }

    private String getCommandLine(String fileName, String commands) {
        try {
            String[] command = {fileName, commands};
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    if(line.contains("auth-token")){
                        return line;
                    }
                }
            } while (line != null);
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}