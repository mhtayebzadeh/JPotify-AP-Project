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
        this.setLayout(new GridLayout(0, 4));
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
                    new PlaySongMouseListener(this));
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
        private CenterPanel centerPanel;

        public PlaySongMouseListener(CenterPanel centerPanel) {
            this.centerPanel = centerPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == null)
                return;
            EntityPanel entityPanel = (EntityPanel) e.getSource();
            Entity entity = this.centerPanel.library.get(entityPanel.getIndex());
            if (entity instanceof Song)
                this.centerPanel.player.setSong((Song) entity);
            else {
                this.centerPanel.player.setPlayList((Playlist) entity);
                this.centerPanel.library = new ArrayList<>();
                if (entity instanceof Album)
                    this.centerPanel.library.addAll(((Album) entity).getSongs());
                else if (entity instanceof Artist)
                    this.centerPanel.library.addAll(((Artist) entity).getSongs());
                else
                    this.centerPanel.library.addAll(((Playlist) entity).getSongs());
                this.centerPanel.paint();
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}