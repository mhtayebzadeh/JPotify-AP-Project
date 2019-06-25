package JPotifyGUI.LeftPanel;

import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;

public class LeftPanelsPlaylistsPanel extends JPanel {
    private FileManager fileManager;
    private JList<String> playlists;
    private static final Color sideColorBlack = new Color(15, 15, 15);
    private static final Color captionColorGrey = new Color(180, 180, 180);
    private static final Color bottomColorBlack = new Color(100, 100, 100);

    public LeftPanelsPlaylistsPanel(FileManager fileManager) {
        super();
        this.fileManager = fileManager;
        this.setBackground(sideColorBlack);
        DefaultListModel<String> list = new DefaultListModel<>();

        this.playlists = new JList<>(list);
        this.playlists.setBackground(sideColorBlack);
        this.playlists.setForeground(captionColorGrey);

        for (Playlist playlist : this.fileManager.getPlaylists())
            list.addElement(playlist.getTitle());

        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("Playlists");
        label.setForeground(bottomColorBlack);
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
        this.playlists.setBackground(sideColorBlack);
        this.playlists.setForeground(captionColorGrey);
        this.add(this.playlists, BorderLayout.CENTER);
        this.revalidate();
    }
}