package JPotifyLogic.Library;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Playlist.Playlist;

import java.util.ArrayList;

public class Playlists extends Library {
    public void addPlaylist(Playlist playlist) { this.addEntity(playlist); }

    @Override
    public ArrayList<Entity> getEntities() {
        return super.getEntities();
    }
}
