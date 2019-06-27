package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeftPanelsPlaylistsPanel extends JPanel {
    private JList<String> playlists;
    private LeftPanel leftPanel;

    public LeftPanelsPlaylistsPanel(LeftPanel leftPanel) {
        super();
        this.leftPanel = leftPanel;
        this.setBackground(GUI.sideColorBlack);
        this.setLayout(new BorderLayout());
        DefaultListModel<String> list = new DefaultListModel<>();

        JLabel label = new JLabel("Playlists");
        label.setForeground(GUI.bottomColorBlack);
        label.setPreferredSize(new Dimension(30, 30));

        this.playlists = new JList<>(list);
        this.playlists.setBackground(GUI.sideColorBlack);
        this.playlists.setForeground(GUI.captionColorGrey);
        for (Playlist playlist : leftPanel.getFileManager().getPlaylists())
            list.addElement(playlist.getTitle());
        this.playlists.addMouseListener(new PlaylistsMouseListener(playlists, leftPanel));

        this.add(label, BorderLayout.NORTH);
        this.add(this.playlists, BorderLayout.CENTER);
    }

    public void paint() {
        this.remove(this.playlists);
        DefaultListModel<String> list = new DefaultListModel<>();
        for (Playlist playlist : this.leftPanel.getFileManager().getPlaylists())
            list.addElement(playlist.getTitle());
        this.playlists = new JList<>(list);
        this.playlists.setBackground(GUI.sideColorBlack);
        this.playlists.setForeground(GUI.captionColorGrey);
        this.add(this.playlists, BorderLayout.CENTER);
        this.revalidate();
    }

    private class PlaylistsMouseListener implements MouseListener {
        private JList<String> playlists;
        private LeftPanel leftPanel;

        public PlaylistsMouseListener(JList<String> playlists, LeftPanel leftPanel) {
            this.playlists = playlists;
            this.leftPanel = leftPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (this.playlists.getSelectedIndex() != -1) {
                CenterPanel centerPanel = this.leftPanel.getCenterPanel();
                String selectedValue = this.playlists.getSelectedValue();
                Playlist playlist = this.leftPanel.getFileManager().getPlaylistFromName(selectedValue);

                centerPanel.setLibraryFromSongs(playlist.getSongs());
                centerPanel.getPlayer().setCurrentPlaylist(playlist);
                centerPanel.paint();

                centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().setPlayer(centerPanel.getPlayer());
                centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().paint();
                centerPanel.getLeftPanel().setImageData(playlist.getSongs().get(0).getImageData());
            }
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