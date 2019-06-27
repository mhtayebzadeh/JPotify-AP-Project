package JPotifyGUI.BottomPanel;

import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private Player player;
    private BottomPanelsCurrentMusicPanel bottomPanelsCurrentMusicPanel;
    private BottomPanelSoundControl bottomPanelSoundControl;
    private BottomPanelsMusicControlPanel bottomPanelsMusicControlPanel;
    public BottomPanel(Player player) {
        super();
        this.player = player;
        this.setLayout(new BorderLayout());
        this.bottomPanelSoundControl = new BottomPanelSoundControl();
        this.bottomPanelsCurrentMusicPanel = new BottomPanelsCurrentMusicPanel(player);
        bottomPanelsMusicControlPanel = new BottomPanelsMusicControlPanel(player);
        this.add(bottomPanelsMusicControlPanel, BorderLayout.CENTER);
        this.add(this.bottomPanelsCurrentMusicPanel, BorderLayout.WEST);
        this.add(this.bottomPanelSoundControl,BorderLayout.EAST);
        /*this.add(playButton, BorderLayout.CENTER);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("play pressed at " + e.getWhen());
            }
        });*/
    }

    public void setMusicSlider(int val,int min , int max)
    {
        this.bottomPanelsMusicControlPanel.setMusicSliderInitValue(val,min,max);
    }
    public BottomPanelsCurrentMusicPanel getBottomPanelsCurrentMusicPanel() {
        return bottomPanelsCurrentMusicPanel;
    }
}
