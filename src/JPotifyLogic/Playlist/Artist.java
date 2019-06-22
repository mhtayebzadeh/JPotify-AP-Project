package JPotifyLogic.Playlist;

public class Artist extends Playlist {
    private String artist = "";
    public Artist(String artist)
    {
        super();
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }
}
