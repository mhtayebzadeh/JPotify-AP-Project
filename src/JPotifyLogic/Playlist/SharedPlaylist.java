package JPotifyLogic.Playlist;

public class SharedPlaylist extends Playlist{
    public SharedPlaylist()
    {
        super();
        this.setTypeOfPlaylist("shared");
    }
    public SharedPlaylist(Playlist playlist)
    {
        super();
        this.setTypeOfPlaylist("shared");
        this.setTitle(playlist.getTitle());
        this.setCaption(playlist.getCaption());
        this.setImageData(playlist.getImageData());
        this.setSongs(playlist.getSongs());
    }
}
