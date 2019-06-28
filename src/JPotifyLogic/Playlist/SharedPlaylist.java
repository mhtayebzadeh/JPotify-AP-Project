package JPotifyLogic.Playlist;

import com.sun.corba.se.spi.activation.Server;

import java.io.Serializable;

/**
 * a child of playlist where one single shared playlist object is made
 * songs to shared playlist are added automatically when a song is shared
 */
public class SharedPlaylist extends Playlist implements Serializable {
    public SharedPlaylist() {
        super("Shared Playlist");
        this.setTypeOfPlaylist("shared");
    }

    public SharedPlaylist(Playlist playlist) {
        super();
        this.setTypeOfPlaylist("shared");
        this.setTitle(playlist.getTitle());
        this.setCaption(playlist.getCaption());
        this.setImageData(playlist.getImageData());
        this.setSongs(playlist.getSongs());
    }
}
