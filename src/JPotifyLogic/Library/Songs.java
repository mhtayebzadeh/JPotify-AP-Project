package JPotifyLogic.Library;

import JPotifyLogic.Entity.Song;

public class Songs extends Library {
    public void addSong(Song song) {
        this.getEntities().add(song);
    }
}
