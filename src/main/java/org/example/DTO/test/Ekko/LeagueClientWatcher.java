package org.example.DTO.test.Ekko;


import org.example.DTO.test.Ekko.platform.LinuxPlatform;
import org.example.DTO.test.Ekko.platform.OsxPlatform;
import org.example.DTO.test.Ekko.platform.PlatformBase;
import org.example.DTO.test.Ekko.platform.WindowsPlatform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LeagueClientWatcher {
    private final PlatformBase platform;

    public LeagueClientWatcher() {
        platform = System.getProperty("os.name").toLowerCase().contains("win") ? new WindowsPlatform() :
                System.getProperty("os.name").toLowerCase().contains("mac") ? new OsxPlatform() : new LinuxPlatform();
        clients = new ArrayList<>();
    }

    private final List<LeagueClient> clients;
    private Consumer<BiConsumer<LeagueClientWatcher, LeagueClient>> onLeagueClient = (c) -> {};
    private Consumer<BiConsumer<LeagueClientWatcher, LeagueClient>> onLeagueClientExit;

    public void observe() {
        AtomicBoolean found = new AtomicBoolean(false);
        while (!found.get()) {
            if (Thread.currentThread().isInterrupted())
                return;

            ProcessHandle.allProcesses().forEach(process -> {
                String processName = process.info().command().orElse("");
                if (processName.equals("LeagueClientUx") || processName.endsWith("LeagueClientUx.exe") || processName.equals("C:\\Riot Games\\League of Legends\\LeagueClientUx.exe")) { // Wine turns it into an .exe process.
                    if (clients.stream().anyMatch(client -> client.getPid() == process.pid()))
                        return;
                    for(LeagueClient client: clients){
                        if(client.getPid() == process.pid()){
                            System.out.println(client.getPid());
                        }
                    }
                    ClientAuthInfo info = platform.extractArguments(process.pid());
                    if (info == null)
                        return;
                    LeagueClient client = new LeagueClient(process.pid(), info, process);
                    //System.out.println("AuthToken: " + client.getClientAuthInfo().getRiotClientAuthToken());
                    //System.out.println("ClientPort: " + client.getClientAuthInfo().getRiotClientPort());
                    //System.out.println("RemotingAuthToken: " + client.getClientAuthInfo().getRemotingAuthToken());
                    //System.out.println("Remoting Port: " + client.getClientAuthInfo().getRemotingPort());
                    process.onExit().whenComplete((unused, throwable) -> {
                        clients.remove(client);
                        onLeagueClientExit.accept((watcher, c) -> watcher.onLeagueClientExit(c));
                    });
                    clients.add(client);
                    onLeagueClient.accept((watcher, c) -> watcher.onLeagueClient(c));
                    found.set(true);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                return;
            }
        }
    }

    public void setOnLeagueClient(Consumer<BiConsumer<LeagueClientWatcher, LeagueClient>> onLeagueClient) {
        this.onLeagueClient = onLeagueClient;
    }

    public void setOnLeagueClientExit(Consumer<BiConsumer<LeagueClientWatcher, LeagueClient>> onLeagueClientExit) {
        this.onLeagueClientExit = onLeagueClientExit;
    }

    private void onLeagueClient(LeagueClient client) {
        if (onLeagueClient != null) {
            onLeagueClient.accept((watcher, c) -> watcher.onLeagueClient(c));
        }
    }

    private void onLeagueClientExit(LeagueClient client) {
        if (onLeagueClientExit != null) {
            onLeagueClientExit.accept((watcher, c) -> watcher.onLeagueClientExit(c));
        }
    }

    public ClientAuthInfo getAuthInfo(){
        return this.clients.get(0).getClientAuthInfo();
    }
}