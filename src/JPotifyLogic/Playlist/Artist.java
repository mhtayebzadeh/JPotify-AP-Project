package JPotifyLogic.Playlist;

public class Artist extends Playlist {

    public Artist(String artist) {
        super();
        this.setTitle(artist);
    }

    public String getArtist() {
        return this.getTitle();
    }
}
