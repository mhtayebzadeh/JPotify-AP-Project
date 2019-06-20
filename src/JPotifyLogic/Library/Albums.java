package JPotifyLogic.Library;

import JPotifyLogic.Playlist.Album;

public class Albums extends Library {
    public void addAlbum(Album album) {
        this.getEntities().add(album);
    }
}
