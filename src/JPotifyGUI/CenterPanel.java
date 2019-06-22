package JPotifyGUI;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Player;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CenterPanel extends JPanel {
    private ArrayList<Entity> library;
    private ArrayList<EntityPanel> entityPanels;
    private Player player;

    public CenterPanel(Player player) {
        super();
        this.setBackground(Color.BLACK);
        this.entityPanels = new ArrayList<>();
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
            EntityPanel entityPanel = new EntityPanel(entity.getTitle(),
                    entity.getCaption(), entity.getImageData(), i);
            this.entityPanels.add(entityPanel);
            this.add(entityPanel);
        }
        for (int i = 0; i < this.library.size(); i++)
            this.entityPanels.get(i).addMouseListener(
                    new PlaySongMouseListener(this.library, this.player));
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

    public class PlaySongMouseListener implements MouseListener {
        private ArrayList<Entity> library;
        private Player player;

        public PlaySongMouseListener(ArrayList<Entity> library, Player player) {
            this.library = library;
            this.player = player;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("naxabar?");
            if (e.getSource() == null)
                return;
            System.out.println(e.getX());
            EntityPanel entityPanel = (EntityPanel) e.getSource();
            System.out.println(entityPanel.getIndex());
            this.player.setSong((Song) this.library.get(entityPanel.getIndex()));
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}