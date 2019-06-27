package JPotifyGUI.CenterPanel;

import JPotifyGUI.BottomPanel.BottomPanel;
import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Player;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel {
    private static final Color bgColorBlack = new Color(43, 43, 43);
    private ArrayList<Entity> library;
    private ArrayList<EntityPanel> entityPanels;
    private Player player;
    private BottomPanel bottomPanel;
    private LeftPanel leftPanel;

    public CenterPanel(Player player, BottomPanel bottomPanel, LeftPanel leftPanel) {
        super();
        this.setBackground(bgColorBlack);
        this.entityPanels = new ArrayList<>();
        this.library = new ArrayList<>();
        this.setLayout(new GridLayout(0, 3));
        this.player = player;
        this.bottomPanel = bottomPanel;
        this.leftPanel = leftPanel;
    }

    /*public void setSongs(ArrayList<SongPanel> songs) {
        this.songs = songs;
        this.removeAll();
        for (SongPanel s : songs) {
            s.setMaximumSize(new Dimension(20, 30));
            this.add(s);
        }
        this.validate();
    }*/

    public void paint() {
        this.removeAll();
        this.entityPanels = new ArrayList<>();
        for (Entity entity : this.library) {
            EntityPanel entityPanel = new EntityPanel(this, entity);
            this.entityPanels.add(entityPanel);
            this.add(entityPanel);
        }
        this.revalidate();
        this.repaint();
    }


    public void setLibraryFromSongs(ArrayList<Song> songs) {
        this.library = new ArrayList<>();
        this.library.addAll(songs);
    }

    public void setLibraryFromPlaylists(ArrayList<Playlist> playlists) {
        this.library = new ArrayList<>();
        this.library.addAll(playlists);
    }

    public void setLibraryFromAlbums(ArrayList<Album> albums) {
        this.library = new ArrayList<>();
        this.library.addAll(albums);
    }

    public void setLibraryFromArtists(ArrayList<Artist> artists) {
        this.library = new ArrayList<>();
        this.library.addAll(artists);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }
}