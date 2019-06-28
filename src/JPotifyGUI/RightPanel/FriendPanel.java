package JPotifyGUI.RightPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Network.Friend;

import javax.swing.*;
import java.awt.*;

public class FriendPanel extends JPanel {
    public FriendPanel(Friend friend) {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.sideColorBlack);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GUI.sideColorBlack);

        JLabel namePanel = new JLabel(friend.getName());
        namePanel.setForeground(Color.WHITE);
        mainPanel.add(namePanel, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(GUI.sideColorBlack);
        if (friend.getLastArtwork() != null) {
            JLabel titleLabel = new JLabel(friend.getLastArtwork().getTitle());
            JLabel captionLabel = new JLabel(friend.getLastArtwork().getCaption());
            titleLabel.setForeground(GUI.captionColorGrey);
            captionLabel.setForeground(GUI.captionColorGrey);

            subPanel.add(titleLabel, BorderLayout.CENTER);
            subPanel.add(captionLabel, BorderLayout.SOUTH);
        }
        mainPanel.add(subPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.SOUTH);
    }
}