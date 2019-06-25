package JPotifyGUI.BottomPanel;

import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

public class BottomPanelsCurrentMusicPanel extends JPanel {
    private Player player;
    private static final Color captionColorGrey = new Color(180, 180, 180);
    private static final Color bottomColorBlack = new Color(100, 100, 100);

    public BottomPanelsCurrentMusicPanel(Player player) {
        super();
        this.player = player;

        this.setLayout(new GridLayout(2, 1));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, dim.height/64));
        this.setBackground(bottomColorBlack);

        if (this.player != null) {
            JLabel titleLabel = new JLabel(this.player.getSong().getTitle());
            titleLabel.setForeground(Color.WHITE);
            JLabel captionLabel = new JLabel(this.player.getSong().getArtist());
            captionLabel.setForeground(captionColorGrey);
            this.add(titleLabel);
            this.add(captionLabel);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.removeAll();
        JLabel titleLabel = new JLabel(this.player.getSong().getTitle());
        titleLabel.setForeground(Color.WHITE);
        JLabel captionLabel = new JLabel(this.player.getSong().getArtist());
        captionLabel.setForeground(captionColorGrey);
        this.add(titleLabel);
        this.add(captionLabel);
        this.revalidate();
    }
}
