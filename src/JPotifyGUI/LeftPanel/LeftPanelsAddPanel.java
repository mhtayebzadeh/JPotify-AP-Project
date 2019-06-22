package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.LogicData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class LeftPanelsAddPanel extends JPanel {
    private LogicData logicData;
    private CenterPanel centerPanel;

    // TODO: NewPlaylistMouseListener not implemented
    public LeftPanelsAddPanel(LogicData logicData, CenterPanel centerPanel) {
        super();
        this.logicData = logicData;
        this.centerPanel = centerPanel;

        this.setLayout(new GridLayout(2, 1));

        JPanel newSongPanel = new JPanel();
        newSongPanel.setLayout(new GridLayout(1, 2));
        JButton newSongButton = new JButton();
        newSongButton.addMouseListener(new NewSongMouseListener(this.logicData, centerPanel));
        JLabel newSongLabel = new JLabel("New Song");

        JPanel newPlaylistPanel = new JPanel();
        newPlaylistPanel.setLayout(new GridLayout(1, 2));
        JButton newPlaylistButton = new JButton();
        newPlaylistButton.addMouseListener(new NewPlaylistMouseListener(this.logicData, centerPanel));
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
        private LogicData logicData;
        private CenterPanel centerPanel;

        public NewSongMouseListener(LogicData logicData, CenterPanel centerPanel) {
            this.logicData = logicData;
            this.centerPanel = centerPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int res = fileChooser.showOpenDialog(new Frame());
            if (res == JFileChooser.CANCEL_OPTION)
                return;
            File file = fileChooser.getSelectedFile();
            Song song = new Song(file.getAbsolutePath());
            this.logicData.addSong(song);
            this.centerPanel.setLibraryFromSongs(this.logicData.getSongs());
            this.centerPanel.paint();
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
        private LogicData logicData;
        private CenterPanel centerPanel;

        public NewPlaylistMouseListener(LogicData logicData, CenterPanel centerPanel) {
            this.logicData = logicData;
            this.centerPanel = centerPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO
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
