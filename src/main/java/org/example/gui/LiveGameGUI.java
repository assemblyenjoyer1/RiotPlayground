package org.example.gui;

import org.example.DTO.models.enums.MatchRegion;
import org.example.DTO.models.enums.Region;
import org.example.DTO.models.v4Summoner.Summoner;
import org.example.DTO.models.v4league.LeagueEntry;
import org.example.DTO.models.v4spectator.CurrentGameParticipant;
import org.example.DTO.models.v5Match.Match;
import org.example.DTO.models.v5Match.Participant;
import org.example.DTO.test.Transformer;
import org.example.services.LeagueService;
import org.example.services.MatchService;
import org.example.services.PlayerService;
import org.example.services.SecretService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LiveGameGUI extends JFrame {
    private JTextField nameTextField, regionTextField, historyNameTextField, apiKeyTextField, nameTextFieldSummonerInfo, summonerInfoTextField;
    private JButton retrieveButton, resetButton, setApiKey, retrieveMatchHistoryButton, retrieveSummonerInfoButton;
    private JTextArea liveGameDataTextArea, matchHistoryDataTextArea;
    private JComboBox<Region> regionComboBox, regionComboBox2, regionComboBoxSummonerInfo;
    private JComboBox<MatchRegion> matchRegionComboBox;
    static String apiKey = System.getenv("APIKEY");
    static PlayerService playerService = new PlayerService(apiKey);
    static SecretService secretService = new SecretService();
    static boolean authenticated = false;

        public LiveGameGUI() {
            super("Testing stuff GUI");
            MatchService matchService = new MatchService(playerService);
            regionComboBox = new JComboBox<>(Region.values());
            regionComboBox2 = new JComboBox<>(Region.values());
            regionComboBoxSummonerInfo = new JComboBox<>(Region.values());
            matchRegionComboBox = new JComboBox<>(MatchRegion.values());
            nameTextField = new JTextField(20);
            nameTextFieldSummonerInfo = new JTextField(20);
            summonerInfoTextField = new JTextField(20);
            regionTextField = new JTextField(20);
            retrieveButton = new JButton("Retrieve Live Game Data");
            resetButton = new JButton("Reset");
            liveGameDataTextArea = new JTextArea(10, 40);
            liveGameDataTextArea.setEditable(false);
            matchHistoryDataTextArea = new JTextArea(10, 40);
            matchHistoryDataTextArea.setEditable(false);
            apiKeyTextField = new JTextField(20);
            setApiKey = new JButton("Set api key");
            historyNameTextField = new JTextField(20);
            retrieveMatchHistoryButton = new JButton("Retrieve");
            retrieveSummonerInfoButton = new JButton("Retrieve");

            // Add listeners to buttons
            retrieveButton.addActionListener(e -> {
                if (authenticated || isPasswordCorrect()) {
                    String name = nameTextField.getText();
                    Region region = (Region) regionComboBox.getSelectedItem();
                    List<CurrentGameParticipant> currentGameParticipants = matchService.getCurrentGameParticipants(region, name);
                    updateLiveGameDataTextArea(region, currentGameParticipants);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            resetButton.addActionListener(e -> {
                nameTextField.setText("");
                liveGameDataTextArea.setText("");
            });

            setApiKey.addActionListener(e -> {
                String newKey = apiKeyTextField.getText();
                playerService.setApiKey(newKey);
                apiKeyTextField.setText("");
                JOptionPane.showMessageDialog(this, "Successfully set api key", "Success", JOptionPane.INFORMATION_MESSAGE);
            });

            retrieveMatchHistoryButton.addActionListener(e -> {
                Region region = (Region) regionComboBox2.getSelectedItem();
                MatchRegion matchRegion = (MatchRegion) matchRegionComboBox.getSelectedItem();
                String name = historyNameTextField.getText();
                updateMatchHistoryTextArea(region, matchRegion, name);
            });

            retrieveSummonerInfoButton.addActionListener( e -> {
                if (authenticated || isPasswordCorrect()) {
                    System.out.println(playerService.getApiKey());
                    String name = nameTextFieldSummonerInfo.getText();
                    Region region = (Region) regionComboBoxSummonerInfo.getSelectedItem();
                    Summoner summoner = playerService.getSummonerByName(region, name);
                    System.out.println(summoner.getAccountId());
                    updateSummonerInfoTextArea(summoner);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Create the first panel and add components to it
            JPanel panelOne = new JPanel(new BorderLayout());
            panelOne.setBorder(BorderFactory.createTitledBorder("Live Game Data"));
            JPanel topPanel = new JPanel(new GridLayout());
            topPanel.add(new JLabel("Enter your name:"));
            topPanel.add(nameTextField);
            topPanel.add(new JLabel("Select your region:"));
            topPanel.add(regionComboBox);
            panelOne.add(topPanel, BorderLayout.NORTH);
            panelOne.add(new JScrollPane(liveGameDataTextArea), BorderLayout.CENTER);
            JPanel bottomPanel = new JPanel(new FlowLayout());
            bottomPanel.add(retrieveButton);
            bottomPanel.add(resetButton);
            panelOne.add(bottomPanel, BorderLayout.SOUTH);

            // Create the second panel and add components to it
            JPanel panelTwo = new JPanel(new BorderLayout());
            panelTwo.setBorder(BorderFactory.createTitledBorder("Match History"));
            JPanel topPanelTwo = new JPanel(new GridLayout());
            topPanelTwo.add(new JLabel("Name:"));
            topPanelTwo.add(historyNameTextField);
            topPanelTwo.add(new JLabel("Region:"));
            topPanelTwo.add(regionComboBox2);
            topPanelTwo.add(new JLabel("Matchregion:"));
            topPanelTwo.add(matchRegionComboBox);
            panelTwo.add(new JScrollPane(matchHistoryDataTextArea), BorderLayout.CENTER);
            panelTwo.add(topPanelTwo, BorderLayout.NORTH);
            panelTwo.add(retrieveMatchHistoryButton, BorderLayout.SOUTH);

            // Create the third panel and add components to it
            JPanel panelThree = new JPanel(new BorderLayout());
            panelThree.setBorder(BorderFactory.createTitledBorder("API Key"));
            JPanel topPanelThree = new JPanel(new FlowLayout());
            topPanelThree.add(new JLabel("Enter your api key:"));
            topPanelThree.add(apiKeyTextField);
            panelThree.add(topPanelThree, BorderLayout.NORTH);
            panelThree.add(setApiKey, BorderLayout.SOUTH);

            // Create the fourth panel and add components to it
            JPanel panelFour = new JPanel(new BorderLayout());
            panelFour.setBorder(BorderFactory.createTitledBorder("SummonerDTO"));
            JPanel topPanelFour = new JPanel(new GridLayout());
            topPanelFour.add(new JLabel("Enter your Summoner name:"));
            topPanelFour.add(nameTextFieldSummonerInfo);
            topPanelFour.add(new JLabel("Select your region:"));
            topPanelFour.add(regionComboBoxSummonerInfo);
            panelFour.add(topPanelFour, BorderLayout.NORTH);
            panelFour.add(new JScrollPane(summonerInfoTextField), BorderLayout.CENTER);
            JPanel bottomPanelFour = new JPanel(new FlowLayout());
            bottomPanelFour.add(retrieveSummonerInfoButton);
            panelFour.add(bottomPanelFour, BorderLayout.SOUTH);


            // Create the JTabbedPane and add the panels to it
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Live Game Data", panelOne);
            tabbedPane.addTab("Match History", panelTwo);
            tabbedPane.addTab("API Key", panelThree);
            tabbedPane.addTab("SummonerDTO", panelFour);

            // Add the JTabbedPane to the JFrame
            add(tabbedPane);

            // Set the JFrame properties
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 800);
            setVisible(true);
        }


        private void updateLiveGameDataTextArea(Region region, List<CurrentGameParticipant> currentGameParticipants) {
            LeagueService leagueService = new LeagueService();
            StringBuilder sb = new StringBuilder();
            if (currentGameParticipants == null) {
                sb.append("Summoner is not ingame");
            } else {
                for (CurrentGameParticipant participant : currentGameParticipants) {
                    Set<LeagueEntry> leagueEntries = leagueService.getLeagueEntryByEncryptedSummonerId(region, participant.getSummonerId());
                    List<LeagueEntry> leagueEntryList = new ArrayList<>(leagueEntries);
                    LeagueEntry desiredEntry = null;
                    for(LeagueEntry leagueEntry: leagueEntryList){
                        if(leagueEntry.getQueueType().equals("RANKED_SOLO_5x5")){
                            desiredEntry = leagueEntry;
                        }
                    }
                    String playerWinrate = leagueService.calculateWinrate(desiredEntry.getWins(), desiredEntry.getLosses());
                    sb.append(participant.getSummonerName()).append(" is currently playing ").append(Transformer.getChampionNameById(participant.getChampionId())).append(" - " + desiredEntry.getTier() + " ").append(desiredEntry.getRank()).append(" | " + desiredEntry.getLeaguePoints() + " LP").append(" - " + playerWinrate).append((desiredEntry.getWins() + desiredEntry.getLosses()) + " Games").append("\n");
                }
            }
            liveGameDataTextArea.setText(sb.toString());
        }

        private void updateSummonerInfoTextArea(Summoner summoner){
            StringBuilder sb = new StringBuilder();
            sb.append("Name: " + summoner.getName()).append("\n")
                    .append("\nAccountID: " + summoner.getAccountId())
                    .append("\nID: " + summoner.getId())
                    .append("\nPUUID: " + summoner.getPuuid())
                    .append("\nLevel:" + summoner.getSummonerLevel());
            summonerInfoTextField.setText(sb.toString());
        }

        private void updateMatchHistoryTextArea(Region region, MatchRegion matchRegion, String name){
            StringBuilder sb = new StringBuilder();
            String[] data = playerService.getSummonerData(name, region, MatchRegion.EUROPE);
            for(String s: data){
                Match match = playerService.getMatchHistoryData(matchRegion, s);
                for(Participant participant: match.getInfo().getParticipants()){
                    if(participant.getSummonerName().equals(name)){
                        sb.append(Transformer.getQueueTypeByQueueID(match.getInfo().getQueueId()) + " | ").append(Transformer.getChampionNameById(participant.getChampionId())).append(" | ").append(participant.getKills()).append("/").append(participant.getDeaths()).append("/").append(participant.getAssists()).append("\n");
                    }
                }
            }
            matchHistoryDataTextArea.setText(sb.toString());
        }

    private boolean isPasswordCorrect() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            String passwordString = new String(password);
            authenticated = true;
            return secretService.comparePassword(passwordString);
        }
        return false;
    }

}
