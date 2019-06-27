package JPotifyGUI.LeftPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;

public class LeftPanelsPlaylistsPanel extends JPanel {
    private FileManager fileManager;
    private JList<String> playlists;

    public LeftPanelsPlaylistsPanel(FileManager fileManager) {
        super();
        this.fileManager = fileManager;
        this.setBackground(GUI.sideColorBlack);
        DefaultListModel<String> list = new DefaultListModel<>();

        this.playlists = new JList<>(list);
        this.playlists.setBackground(GUI.sideColorBlack);
        this.playlists.setForeground(GUI.captionColorGrey);

        for (Playlist playlist : this.fileManager.getPlaylists())
            list.addElement(playlist.getTitle());

        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("Playlists");
        label.setForeground(GUI.bottomColorBlack);
        label.setPreferredSize(new Dimension(30, 30));
        this.add(label, BorderLayout.NORTH);
        this.add(this.playlists, BorderLayout.CENTER);
    }

    public void paint() {
        this.remove(this.playlists);
        DefaultListModel<String> list = new DefaultListModel<>();
        for (Playlist playlist : this.fileManager.getPlaylists()) {
            list.addElement(playlist.getTitle());
            System.out.println(playlist.getTitle());
        }
        this.playlists = new JList<>(list);
        this.playlists.setBackground(GUI.sideColorBlack);
        this.playlists.setForeground(GUI.captionColorGrey);
        this.add(this.playlists, BorderLayout.CENTER);
        this.revalidate();
    }
}