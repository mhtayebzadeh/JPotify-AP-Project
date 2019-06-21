package JPotifyLogic.Library;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;

import java.util.ArrayList;

public class Songs extends Library {
    public ArrayList<Entity> getSongs() { return this.getEntities(); }
    public void addSong(Song song) { this.addEntity(song); }
}
