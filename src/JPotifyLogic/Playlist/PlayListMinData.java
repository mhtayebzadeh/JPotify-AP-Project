package JPotifyLogic.Playlist;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.SongMinimumData;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayListMinData extends Entity implements Serializable {

    private ArrayList<SongMinimumData> songsMinData = new ArrayList<SongMinimumData>();

    public PlayListMinData(String title, String caption, byte[] imageData) {
        super(title, caption, imageData);
    }

    public void addSongsMinData(SongMinimumData songMinData) {
        this.songsMinData.add(songMinData);
    }

    public ArrayList<SongMinimumData> getSongsMinData() {
        return songsMinData;
    }

    public void setSongsMinData(ArrayList<SongMinimumData> songsMinData) {
        this.songsMinData = songsMinData;
    }
}
