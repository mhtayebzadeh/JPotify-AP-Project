package JPotifyLogic.Playlist;

public class Album extends Playlist {
    private String album = "";

    public Album(String album) {
        super();
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }
}
