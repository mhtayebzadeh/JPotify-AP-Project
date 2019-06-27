package JPotifyGUI.BottomPanel;

import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * this class is created as GUI's bottom panel
 * and its own components
 */
public class BottomPanel extends JPanel {
    private BottomPanelsCurrentMusicPanel bottomPanelsCurrentMusicPanel;

    /**
     * @param player gets an object from Player class to use music controls on it
     *               and also fetch current music data and display it
     */
    public BottomPanel(Player player) {
        super();
        this.setLayout(new BorderLayout());
        this.bottomPanelsCurrentMusicPanel = new BottomPanelsCurrentMusicPanel(player);
        this.add(new BottomPanelsMusicControlPanel(player), BorderLayout.CENTER);
        this.add(this.bottomPanelsCurrentMusicPanel, BorderLayout.WEST);
    }

    public BottomPanelsCurrentMusicPanel getBottomPanelsCurrentMusicPanel() {
        return bottomPanelsCurrentMusicPanel;
    }
}
