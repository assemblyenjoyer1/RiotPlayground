package org.example.gui;

import org.example.DTO.models.enums.Region;
import org.example.DTO.models.v4.CurrentGameParticipant;
import org.example.DTO.test.Transformer;
import org.example.services.MatchService;
import org.example.services.PlayerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LiveGameGUI extends JFrame {
    private JTextField nameTextField, regionTextField;
    private JButton retrieveButton;
    private JTextArea liveGameDataTextArea;

    public LiveGameGUI() {
        super("Testing stuff GUI");
        PlayerService playerService = new PlayerService();
        MatchService matchService = new MatchService(playerService);

        // Initialize GUI components
        nameTextField = new JTextField(20);
        JComboBox<Region> regionComboBox = new JComboBox<>(Region.values());
        retrieveButton = new JButton("Retrieve Live Game Data");
        liveGameDataTextArea = new JTextArea(10, 30);

        retrieveButton.addActionListener(e -> {
            String name = nameTextField.getText();
            Region region = (Region) regionComboBox.getSelectedItem();
            List<CurrentGameParticipant> currentGameParticipants = matchService.getCurrentGameParticipants(region, name);
            updateLiveGameDataTextArea(currentGameParticipants);
        });

        // Add components to JFrame
        setLayout(new FlowLayout());
        add(new JLabel("Enter your name:"));
        add(nameTextField);
        add(new JLabel("Select your region:"));
        add(regionComboBox);
        add(retrieveButton);
        add(new JScrollPane(liveGameDataTextArea));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    // Update the liveGameDataTextArea with the live game data
    private void updateLiveGameDataTextArea(List<CurrentGameParticipant> currentGameParticipants) {
        StringBuilder sb = new StringBuilder();
        if (currentGameParticipants == null){
            sb.append("Summoner is not ingame");
        }else{
            for(CurrentGameParticipant participant: currentGameParticipants){
                sb.append(participant.getSummonerName()).append(" is currently playing ").append(Transformer.getChampionNameById(participant.getChampionId())).append("\n");
            }
        }

        liveGameDataTextArea.setText(sb.toString());
    }
}
