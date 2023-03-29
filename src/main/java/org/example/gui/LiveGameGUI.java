package org.example.gui;

import org.example.DTO.models.enums.Region;
import org.example.DTO.models.v4league.LeagueEntry;
import org.example.DTO.models.v4spectator.CurrentGameParticipant;
import org.example.DTO.test.Transformer;
import org.example.services.LeagueService;
import org.example.services.MatchService;
import org.example.services.PlayerService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LiveGameGUI extends JFrame {
    private JTextField nameTextField, regionTextField, historyNameTextField, apiKeyTextField;
    private JButton retrieveButton, resetButton, setApiKey, retrieveMatchHistoryButton;
    private JTextArea liveGameDataTextArea;
    private JComboBox<Region> regionComboBox;
    String apiKey = System.getenv("APIKEY");


        public LiveGameGUI() {
            super("Testing stuff GUI");

            PlayerService playerService = new PlayerService(apiKey);
            MatchService matchService = new MatchService(playerService);


            regionComboBox = new JComboBox<>(Region.values());
            nameTextField = new JTextField(20);
            regionTextField = new JTextField(20);
            retrieveButton = new JButton("Retrieve Live Game Data");
            resetButton = new JButton("reset");
            liveGameDataTextArea = new JTextArea(10, 40);
            apiKeyTextField = new JTextField(20);
            setApiKey = new JButton("set api key");
            historyNameTextField = new JTextField(20);
            retrieveMatchHistoryButton = new JButton("retrieve");

            // Add listeners to buttons
            retrieveButton.addActionListener(e -> {
                String name = nameTextField.getText();
                Region region = (Region) regionComboBox.getSelectedItem();
                List<CurrentGameParticipant> currentGameParticipants = matchService.getCurrentGameParticipants(region, name);
                updateLiveGameDataTextArea(region, currentGameParticipants);
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
            JPanel topPanelTwo = new JPanel(new FlowLayout());
            topPanelTwo.add(new JLabel("Retrieve matchhistory: "));
            topPanelTwo.add(historyNameTextField);
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

            // Create the JTabbedPane and add the panels to it
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Live Game Data", panelOne);
            tabbedPane.addTab("Match History", panelTwo);
            tabbedPane.addTab("API Key", panelThree);

            // Add the JTabbedPane to the JFrame
            add(tabbedPane);

            // Set the JFrame properties
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 800);
            setVisible(true);
        }


        private void updateLiveGameDataTextArea(Region region, List<CurrentGameParticipant> currentGameParticipants) {
            LeagueService leagueService = new LeagueService();
            StringBuilder sb = new StringBuilder();
            if (currentGameParticipants == null) {
                sb.append("Summoner is not ingame");
            } else {
                for (CurrentGameParticipant participant : currentGameParticipants) {
                    Set<LeagueEntry> leagueEntries =leagueService.getLeagueEntryByEncryptedSummonerId(region, participant.getSummonerId());
                    List<LeagueEntry> leagueEntryList = new ArrayList<>(leagueEntries);
                    sb.append(participant.getSummonerName()).append(" is currently playing ").append(Transformer.getChampionNameById(participant.getChampionId())).append(" - " + leagueEntryList.get(0).getTier() + " ").append(leagueEntryList.get(0).getRank()).append(" | ").append("\n");
                }
            }

            liveGameDataTextArea.setText(sb.toString());
        }
    }
