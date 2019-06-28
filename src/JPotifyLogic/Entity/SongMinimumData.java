package JPotifyLogic.Entity;

import java.io.Serializable;

/**
 * a class for gathering the minimum data required to save a song
 * implements Serializable to be able to written as an object
 */
public class SongMinimumData implements Serializable {
    private String fileAddress;
    private String lastPlayed;
    private long pauseLocation;
    private boolean isFavorite;
    private long timeStampLastPlayed;

    /**
     * @param fileAddress         address of the file
     * @param lastPlayed          lastPlayed time in today
     * @param pauseLocation       pauseLocation of the song if the song was paused before closing the program
     * @param timeStampLastPlayed the timeStamp of the time the song was last played
     * @param isFavorite          whether the song is liked or not
     */
    public SongMinimumData(String fileAddress, String lastPlayed, long pauseLocation,
                           long timeStampLastPlayed, boolean isFavorite) {
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }
}
