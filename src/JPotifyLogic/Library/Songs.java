package JPotifyLogic.Library;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;

import java.io.Serializable;
import java.util.ArrayList;

public class Songs extends Library implements Serializable {
    public ArrayList<Entity> getSongs() {
        return this.getEntities();
    }

    public void addSong(Song song) {
        this.addEntity(song);
    }
}
