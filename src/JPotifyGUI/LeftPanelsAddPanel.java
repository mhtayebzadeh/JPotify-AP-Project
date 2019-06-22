package JPotifyGUI;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Library.Songs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class LeftPanelsAddPanel extends JPanel {
    private Songs songs;

    // TODO: NewPlaylistMouseListener not implemented
    public LeftPanelsAddPanel(Songs songs) {
        super();
        this.songs = songs;

        this.setLayout(new GridLayout(2, 1));

        JPanel newSongPanel = new JPanel();
        newSongPanel.setLayout(new GridLayout(1, 2));
        JButton newSongButton = new JButton();
        newSongButton.addMouseListener(new NewSongMouseListener(this.songs));
        JLabel newSongLabel = new JLabel("New Song");

        JPanel newPlaylistPanel = new JPanel();
        newPlaylistPanel.setLayout(new GridLayout(1, 2));
        JButton newPlaylistButton = new JButton();
        newPlaylistButton.addMouseListener(new NewPlaylistMouseListener());
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

    public class NewSongMouseListener implements MouseListener {
        private Songs songs;

        public NewSongMouseListener(Songs songs) { this.songs = songs; }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(new Frame());
            File file = fileChooser.getSelectedFile();
            Song song = new Song(file.getAbsolutePath());
            this.songs.addSong(song);
        }

        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }
    }

    private class NewPlaylistMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
