package JPotifyLogic.Library;

import JPotifyLogic.Playlist.Artist;

public class Artists extends Library {
    public void addArtist(Artist artist) {
        this.getEntities().add(artist);
    }
}
