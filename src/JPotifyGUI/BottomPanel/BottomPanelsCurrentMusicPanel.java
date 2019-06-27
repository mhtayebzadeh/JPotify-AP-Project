package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

public class BottomPanelsCurrentMusicPanel extends JPanel {
    private Player player;

    public BottomPanelsCurrentMusicPanel(Player player) {
        super();
        this.player = player;

        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(GUI.dim.width/8, GUI.dim.height/64));
        this.setBackground(GUI.bottomColorBlack);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void paint() {
        this.removeAll();
        JLabel titleLabel = new JLabel(this.player.getSong().getTitle());
        titleLabel.setForeground(Color.WHITE);
        JLabel captionLabel = new JLabel(this.player.getSong().getArtist());
        captionLabel.setForeground(GUI.captionColorGrey);
        this.add(titleLabel);
        this.add(captionLabel);
        this.revalidate();
    }
}
