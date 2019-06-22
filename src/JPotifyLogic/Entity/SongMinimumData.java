package JPotifyLogic.Entity;

import java.io.Serializable;

public class SongMinimumData implements Serializable {
    private String fileAddress;
    private String lastPlayed;
    private long pauseLocation;

    public SongMinimumData(String fileAddress, String lastPlayed, long pauseLocation) {
        this.fileAddress = fileAddress;
        this.lastPlayed = lastPlayed;
        this.pauseLocation = pauseLocation;
    }

    public long getPauseLocation() {
        return pauseLocation;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public String getFileAddress() {
        return fileAddress;
    }

}
