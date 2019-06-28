package JPotifyLogic.Playlist;

import java.security.Timestamp;

/**
 * a child of playlist where artists songs are made
 * songs to artists are added automatically when a song is added to the library
 */
public class Artist extends Playlist {
    Timestamp timestamp;

    public Artist(String artist) {
        super();
        this.setTitle(artist);
    }
}
