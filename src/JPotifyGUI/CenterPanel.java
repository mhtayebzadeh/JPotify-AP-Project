package JPotifyGUI;

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

    public CenterPanel(Player player) {
        super();
        this.setBackground(bgColorBlack);
        this.entityPanels = new ArrayList<>();
        this.library = new ArrayList<>();
        this.setLayout(new GridLayout(0, 3));
        this.player = player;
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
        for (int i = 0; i < this.library.size(); i++) {
            Entity entity = this.library.get(i);
            EntityPanel entityPanel = new EntityPanel(entity.getTitle(), entity.getCaption(),
                    entity.getImageData(), this, entity);
            this.entityPanels.add(entityPanel);
            this.add(entityPanel);
        }
        this.revalidate();
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
}