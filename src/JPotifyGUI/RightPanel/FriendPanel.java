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
        mainPanel.add(namePanel, BorderLayout.NORTH);
        if (friend.getLastArtwork() != null) {
            mainPanel.add(new JLabel(friend.getLastArtwork().getTitle()), BorderLayout.CENTER);
            mainPanel.add(new JLabel(friend.getLastArtwork().getCaption()), BorderLayout.SOUTH);
        }
        this.add(mainPanel, BorderLayout.CENTER);
    }
}