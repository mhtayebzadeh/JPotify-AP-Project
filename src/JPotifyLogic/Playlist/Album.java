package JPotifyLogic.Playlist;

public class Album extends Playlist {

    public Album(String album) {
        super();
        this.setTitle(album);
    }

    public String getAlbum() {
        return this.getTitle();
    }
}
