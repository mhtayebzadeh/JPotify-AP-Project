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

/**
 * a class in top left corner and a panel for the two buttons
 * add song button and add playlist button
 */
public class LeftPanelsAddPanel extends JPanel {
    LeftPanel leftPanel;
    /**
     * @param leftPanel the whole left panel is needed in this smaller class
     *                  to add playlists to the below playlists panel and also
     *                  add songs to center panel through that left panel's field
     */
    public LeftPanelsAddPanel(LeftPanel leftPanel) {
        super();
        this.setBackground(GUI.sideColorBlack);
        this.leftPanel = leftPanel;
        this.setLayout(new GridLayout(2, 1));
        BorderLayout bb = new BorderLayout();
        bb.setHgap(10);
        JPanel newSongPanel = new JPanel();
        newSongPanel.setBackground(GUI.sideColorBlack);
        newSongPanel.setLayout(bb);
        JLabel newSongButton = new JLabel();
        newSongButton.setBackground(GUI.sideColorBlack);

        newSongButton.addMouseListener(new NewSongMouseListener(this.leftPanel));
        JLabel newSongLabel = new JLabel("  New Song");

        newSongLabel.setForeground(GUI.captionColorGrey);

        JPanel newPlaylistPanel = new JPanel();
        newPlaylistPanel.setBackground(GUI.sideColorBlack);
        BorderLayout b = new BorderLayout();
        b.setHgap(10);
        newPlaylistPanel.setLayout(b);
        JLabel newPlaylistButton = new JLabel();
        newPlaylistButton.setBackground(GUI.sideColorBlack);

        newPlaylistButton.addMouseListener(new NewPlaylistMouseListener(this.leftPanel));
        JLabel newPlaylistLabel = new JLabel("  New Playlist");

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

    /**
     * mouse listener class for when the add song button is clicked
     * only the mouseClicked method is overridden
     */
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

    /**
     * mouse listener class for when the add playlist button is clicked
     * only the mouseClicked method is overridden
     */
    private class NewPlaylistMouseListener implements MouseListener {
        private LeftPanel leftPanel;

        public NewPlaylistMouseListener(LeftPanel leftPanel) {
            this.leftPanel = leftPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            String name = JOptionPane.showInputDialog("Enter Playlist name");
            this.leftPanel.getFileManager().add2PlayLists(new Playlist(name));
            this.leftPanel.getCenterPanel().resetLibrary();
            this.leftPanel.getCenterPanel().paint();
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
