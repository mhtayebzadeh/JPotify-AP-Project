package JPotifyGUI;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel {
    private ArrayList<Entity> library;

    public CenterPanel() {
        super();
        this.setBackground(Color.BLACK);
        // this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new GridLayout(0, 3));
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
        for (Entity entity : this.library) {
            SongPanel songPanel = new SongPanel(entity.getTitle(), entity.getCaption(), entity.getImageData());
            this.add(songPanel);
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
}
