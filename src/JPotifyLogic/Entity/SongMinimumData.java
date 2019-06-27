package JPotifyLogic.Entity;

import java.io.Serializable;

public class SongMinimumData implements Serializable {
    private String fileAddress;
    private String lastPlayed;
    private long pauseLocation;
    private boolean isFavorite;
    private long timeStampLastPlayed;
    public SongMinimumData(String fileAddress,String lastPlayed,long pauseLocation,long timeStampLastPlayed,boolean isFavorite)
    {
        this.timeStampLastPlayed = timeStampLastPlayed;
        this.fileAddress = fileAddress;
        this.lastPlayed = lastPlayed;
        this.pauseLocation = pauseLocation;
        this.isFavorite = isFavorite;
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

    public long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
