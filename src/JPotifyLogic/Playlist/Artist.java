package JPotifyLogic.Playlist;

import java.security.Timestamp;

public class Artist extends Playlist {
    private String artist = "";
    Timestamp timestamp = null;
    public Artist(String artist)
    {
        super();
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }
}
