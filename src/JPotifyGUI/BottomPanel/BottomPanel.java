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
    private BottomPanelsMusicControlPanel bottomPanelsMusicControlPanel;

    /**
     * @param player gets an object from Player class to use music controls on it
     * and also fetch current music data and display it
     */
    public BottomPanel(Player player) {
        super();
        this.setLayout(new BorderLayout());
        BottomPanelSoundControl bottomPanelSoundControl = new BottomPanelSoundControl();
        this.bottomPanelsCurrentMusicPanel = new BottomPanelsCurrentMusicPanel(player);
        this.add(new BottomPanelsMusicControlPanel(player), BorderLayout.CENTER);
        this.add(this.bottomPanelsCurrentMusicPanel, BorderLayout.WEST);
        this.bottomPanelsMusicControlPanel = new BottomPanelsMusicControlPanel(player);
        this.add(bottomPanelsMusicControlPanel, BorderLayout.CENTER);
        this.add(this.bottomPanelsCurrentMusicPanel, BorderLayout.WEST);
        this.add(bottomPanelSoundControl, BorderLayout.EAST);
    }

    public void setMusicSlider(int val, int min, int max) {
        this.bottomPanelsMusicControlPanel.setMusicSliderInitValue(val, min, max);
    }

    public BottomPanelsCurrentMusicPanel getBottomPanelsCurrentMusicPanel() {
        return bottomPanelsCurrentMusicPanel;
    }
}
