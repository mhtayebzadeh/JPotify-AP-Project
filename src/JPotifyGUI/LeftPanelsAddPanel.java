package JPotifyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LeftPanelsAddPanel extends JPanel {
    public LeftPanelsAddPanel() {
        super();
        this.setLayout(new GridLayout(2, 1));

        JPanel newSongPanel = new JPanel();
        newSongPanel.setLayout(new GridLayout(1, 2));
        JButton newSongButton = new JButton();
        JLabel newSongLabel = new JLabel("New Song");

        JPanel newPlaylistPanel = new JPanel();
        newPlaylistPanel.setLayout(new GridLayout(1, 2));
        JButton newPlaylistButton = new JButton();
        JLabel newPlaylistLabel = new JLabel("New Playlist");

        newSongPanel.add(newSongButton);
        newSongPanel.add(newSongLabel);
        newPlaylistPanel.add(newPlaylistButton);
        newPlaylistPanel.add(newPlaylistLabel);
        this.add(newSongPanel);
        this.add(newPlaylistPanel);

        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/add_icon.png")));
            Image image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            newSongButton.setIcon(new ImageIcon(image));
            newPlaylistButton.setIcon(new ImageIcon(image));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
