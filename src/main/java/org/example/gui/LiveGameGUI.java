package org.example.gui;

import org.example.DTO.models.enums.Region;
import org.example.DTO.models.v4spectator.CurrentGameParticipant;
import org.example.DTO.test.Transformer;
import org.example.services.MatchService;
import org.example.services.PlayerService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LiveGameGUI extends JFrame {
    private JTextField nameTextField, regionTextField, historyNameTextField, apiKeyTextField;
    private JButton retrieveButton, resetButton, setApiKey, retrieveMatchHistoryButton;
    private JTextArea liveGameDataTextArea;
    String apiKey = System.getenv("APIKEY");


        public LiveGameGUI() {
            super("Testing stuff GUI");

            PlayerService playerService = new PlayerService(apiKey);
            MatchService matchService = new MatchService(playerService);

            nameTextField = new JTextField(20);
            JComboBox<Region> regionComboBox = new JComboBox<>(Region.values());
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
                updateLiveGameDataTextArea(currentGameParticipants);
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

            // Create panels to organize components
            JPanel panelOne = new JPanel(new GridLayout(0, 1, 10, 5));
            panelOne.setBorder(BorderFactory.createTitledBorder("Live Game Data"));
            panelOne.add(new JLabel("Enter your name:"));
            panelOne.add(nameTextField);
            panelOne.add(new JLabel("Select your region:"));
            panelOne.add(regionComboBox);
            panelOne.add(retrieveButton);
            panelOne.add(new JScrollPane(liveGameDataTextArea));
            panelOne.add(resetButton);

            JPanel panelTwo = new JPanel(new GridLayout(0, 1, 5, 5));
            panelTwo.setBorder(BorderFactory.createTitledBorder("Match History"));
            panelTwo.add(new JLabel("Retrieve matchhistory: "));
            panelTwo.add(historyNameTextField);
            panelTwo.add(retrieveMatchHistoryButton);

            JPanel panelThree = new JPanel(new GridLayout(0, 1, 5, 5));
            panelThree.setBorder(BorderFactory.createTitledBorder("API Key"));
            panelThree.add(new JLabel("Enter your api key:"));
            panelThree.add(apiKeyTextField);
            panelThree.add(setApiKey);

            // Add panels to JFrame
            setLayout(new GridLayout(0, 1, 10, 10));
            add(panelOne);
            add(panelTwo);
            add(panelThree);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 800);
            setVisible(true);
        }


        private void updateLiveGameDataTextArea(List<CurrentGameParticipant> currentGameParticipants) {
            StringBuilder sb = new StringBuilder();
            if (currentGameParticipants == null) {
                sb.append("Summoner is not ingame");
            } else {
                for (CurrentGameParticipant participant : currentGameParticipants) {
                    sb.append(participant.getSummonerName()).append(" is currently playing ").append(Transformer.getChampionNameById(participant.getChampionId())).append("\n");
                }
            }

            liveGameDataTextArea.setText(sb.toString());
        }
    }
