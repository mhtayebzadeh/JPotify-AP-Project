package JPotifyGUI.RightPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Network.Friend;

import javax.swing.*;
import java.awt.*;

public class RightPanelsFriendsPanel extends JPanel {
    private RightPanel rightPanel;
    private JPanel friendsPanel;

    public RightPanelsFriendsPanel(RightPanel rightPanel) {
        super();
        this.rightPanel = rightPanel;
        this.setBackground(GUI.sideColorBlack);
        this.setLayout(new BorderLayout());

        this.friendsPanel = new JPanel();
        this.friendsPanel.setLayout(new GridLayout(0, 1));
        this.friendsPanel.setBackground(GUI.sideColorBlack);

        JLabel label = new JLabel("Friend Activity");
        label.setForeground(GUI.bottomColorBlack);
        label.setPreferredSize(new Dimension(30, 30));

        for (Friend friend : rightPanel.getNetworkManager().getFriendsList())
            this.friendsPanel.add(new FriendPanel(friend));
        // this.friends.addMouseListener(new FriendsMouseListener(friends, rightPanel));

        this.add(label, BorderLayout.NORTH);
        this.add(friendsPanel, BorderLayout.CENTER);
    }

    public void paint() {
        this.remove(this.friendsPanel);
        this.friendsPanel = new JPanel();
        this.friendsPanel.setLayout(new GridLayout(0, 1));
        this.friendsPanel.setBackground(GUI.sideColorBlack);

        for (Friend friend : rightPanel.getNetworkManager().getFriendsList())
            this.friendsPanel.add(new FriendPanel(friend));
        // this.friends.addMouseListener(new FriendsMouseListener(this.friends, this.rightPanel));
        this.add(this.friendsPanel, BorderLayout.CENTER);
        this.revalidate();
    }
}
