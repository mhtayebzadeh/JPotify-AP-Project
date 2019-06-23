package JPotifyGUI.BottomPanel;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

public class BottomPanelsCurrentMusicPanel extends JPanel {
    private Player player;

    public BottomPanelsCurrentMusicPanel(Player player) {
        super();
        this.player = player;
        this.setLayout(new GridLayout(2, 1));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, dim.height/64));

        if (this.player != null) {
            this.add(new JLabel(this.player.getSong().getTitle()));
            this.add(new JLabel(this.player.getSong().getArtist()));
        }
    }
}
