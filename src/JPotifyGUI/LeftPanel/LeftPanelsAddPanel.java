package JPotifyGUI.LeftPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class LeftPanelsAddPanel extends JPanel {
    private LeftPanel leftPanel;
    // TODO: NewPlaylistMouseListener not implemented
    public LeftPanelsAddPanel(LeftPanel leftPanel) {
        super();
        this.leftPanel = leftPanel;
        this.setBackground(GUI.sideColorBlack);

        this.setLayout(new GridLayout(2, 1));

        JPanel newSongPanel = new JPanel();
        newSongPanel.setBackground(GUI.sideColorBlack);
        newSongPanel.setLayout(new BorderLayout());
        JButton newSongButton = new JButton();
        newSongButton.setBackground(GUI.sideColorBlack);
        newSongButton.addMouseListener(new NewSongMouseListener(this.leftPanel));
        JLabel newSongLabel = new JLabel("New Song");
        newSongLabel.setForeground(GUI.captionColorGrey);

        JPanel newPlaylistPanel = new JPanel();
        newPlaylistPanel.setBackground(GUI.sideColorBlack);
        newPlaylistPanel.setLayout(new BorderLayout());
        JButton newPlaylistButton = new JButton();
        newPlaylistButton.setBackground(GUI.sideColorBlack);
        newPlaylistButton.addMouseListener(new NewPlaylistMouseListener(this.leftPanel));
        JLabel newPlaylistLabel = new JLabel("New Playlist");
        newPlaylistLabel.setForeground(GUI.captionColorGrey);

        newSongPanel.add(newSongButton, BorderLayout.WEST);
        newSongPanel.add(newSongLabel, BorderLayout.CENTER);
        newPlaylistPanel.add(newPlaylistButton, BorderLayout.WEST);
        newPlaylistPanel.add(newPlaylistLabel, BorderLayout.CENTER);
        this.add(newSongPanel);
        this.add(newPlaylistPanel);

        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/add and remove/add_icon.png")));
            Image image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            newSongButton.setIcon(new ImageIcon(image));
            newPlaylistButton.setIcon(new ImageIcon(image));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private class NewSongMouseListener implements MouseListener {
        private LeftPanel leftPanel;

        public NewSongMouseListener(LeftPanel leftPanel) {
            this.leftPanel = leftPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser(
                    System.getProperty("user.dir") + "/src/JPotifyLogic/Songs");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = fileChooser.showOpenDialog(new Frame());
            if (res == JFileChooser.CANCEL_OPTION)
                return;
            File file = fileChooser.getSelectedFile();
            Song song = new Song(file.getAbsolutePath());
            if (song.getImageData() != null)
                this.leftPanel.getFileManager().add2Songs(song);
            this.leftPanel.getFileManager().update();
            this.leftPanel.getCenterPanel().setLibraryFromSongs(this.leftPanel.getFileManager().getSongs());
            this.leftPanel.getCenterPanel().paint();
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

    private class NewPlaylistMouseListener implements MouseListener {
        private LeftPanel leftPanel;

        public NewPlaylistMouseListener(LeftPanel leftPanel) {
            this.leftPanel = leftPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            String name = JOptionPane.showInputDialog("Enter Playlist name");
            this.leftPanel.getFileManager().add2PlayLists(new Playlist(name));
            this.leftPanel.getLeftPanelsPlaylistsPanel().paint();
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
