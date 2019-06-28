package JPotifyGUI.RightPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.NetworkManager;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private NetworkManager networkManager;
    private RightPanelsFriendsPanel rightPanelsFriendsPanel;

    public RightPanel(NetworkManager networkManager) {
        super();
        this.networkManager = networkManager;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width / 8, dim.height / 8));
        this.setBackground(GUI.sideColorBlack);
        this.setLayout(new BorderLayout());

        this.rightPanelsFriendsPanel = new RightPanelsFriendsPanel(this);
        JPanel rightScrollPanel = new JPanel();
        rightScrollPanel.setLayout(new BorderLayout());
        JScrollPane rightScrollPane = new JScrollPane(rightScrollPanel);
        rightScrollPanel.add(this.rightPanelsFriendsPanel, BorderLayout.CENTER);

        this.add(new RightPanelsAddPanel(this), BorderLayout.NORTH);
        this.add(rightScrollPane, BorderLayout.CENTER);
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public RightPanelsFriendsPanel getRightPanelsFriendsPanel() {
        return rightPanelsFriendsPanel;
    }
}
