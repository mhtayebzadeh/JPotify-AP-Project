package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * this class is designed as the bottom left corner of the GUI
 * where current playing song's information is displayed
 */
public class BottomPanelsCurrentMusicPanel extends JPanel {
    private Player player;

    /**
     * @param player gets an object from Player class to
     *               fetch current music data and display it
     */
    public BottomPanelsCurrentMusicPanel(Player player) {
        super();
        this.player = player;

        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(GUI.dim.width / 8, GUI.dim.height / 64));
        this.setBackground(GUI.bottomColorBlack);
    }

    /**
     * repaints this panel whenever the song is changed
     */
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}
