package JPotifyLogic.Playlist;

/**
 * a child of playlist where albums are made
 * songs to albums are added automatically when a song is added to the library
 */
public class Album extends Playlist {
    /**
     * @param album name of the album
     */
    public Album(String album) {
        super();
        this.setTitle(album);
    }
}
