package JPotifyLogic.Playlist;

/**
 * a child of playlist where one single favorite playlist object is made
 * songs to favorite playlist are added automatically when a song is liked
 */
public class FavoritePlaylist extends Playlist {
    public FavoritePlaylist() {
        super("Favorite Playlist");
        this.setTypeOfPlaylist("favorite");
    }
}
