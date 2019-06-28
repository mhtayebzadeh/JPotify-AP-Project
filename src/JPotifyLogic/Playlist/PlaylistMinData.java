package JPotifyLogic.Playlist;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.SongMinimumData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * a class where minimum data required to save a playlist is stored
 */
public class PlaylistMinData extends Entity implements Serializable {
    private ArrayList<SongMinimumData> songsMinData = new ArrayList<>();
    private String typeOfPlaylist;

    /**
     * @param title     playlist's title (name)
     * @param caption   playlist's caption (whatever text the developer desired)
     * @param imageData playlist's image data bytes (whatever image the developer wants)
     * @param type      type of the playlist (normal, favorite or shared)
     */
    public PlaylistMinData(String title, String caption, byte[] imageData, String type) {
        super(title, caption, imageData);
        this.typeOfPlaylist = type;
    }

    public String getTypeOfPlaylist() {
        return typeOfPlaylist;
    }

    public void addSongsMinData(SongMinimumData songMinData) {
        this.songsMinData.add(songMinData);
    }

    public ArrayList<SongMinimumData> getSongsMinData() {
        return songsMinData;
    }
}
