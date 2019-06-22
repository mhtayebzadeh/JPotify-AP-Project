package JPotifyLogic.Library;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Playlist.Playlist;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlists extends Library implements Serializable {
    public void addPlaylist(Playlist playlist) {
        this.addEntity(playlist);
    }

    @Override
    public ArrayList<Entity> getEntities() {
        return super.getEntities();
    }
}
