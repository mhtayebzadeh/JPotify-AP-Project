package JPotifyLogic.Entity;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Song class contains very crucial data about playing and working with songs
 */
public class Song extends Entity implements Serializable {
    private String address;
    private String album;
    private String lastPlayed;
    private long timeStampLastPlayed;
    private long pauseLocation;
    private long totalSongLength;
    private long timeInSecond;
    private boolean repeat;
    private boolean paused;
    private boolean isFavorite;
    private FileInputStream fis;
    private Mp3File mp3;
    private AdvancedPlayer player;


    /**
     * @param address gets a file directory and makes a song object from it
     */
    public Song(String address) {
        this.address = address;
        try {
            this.fis = new FileInputStream(this.address);
            this.mp3 = new Mp3File(address);
            this.player = new AdvancedPlayer(this.fis);
            this.totalSongLength = this.fis.available();
            this.isFavorite = false;
            this.timeInSecond = this.mp3.getLengthInSeconds();
        } catch (IOException | UnsupportedTagException | InvalidDataException | JavaLayerException e) {
            e.printStackTrace();
        }

        // TODO: bonus not implemented
        if (this.mp3 != null) {
            this.album = this.mp3.getId3v1Tag().getAlbum();
            this.setTitle(this.mp3.getId3v1Tag().getTitle());

            // this song's artist equals its entity parent's caption
            this.setCaption(this.mp3.getId3v1Tag().getArtist());
            this.setImageData(this.mp3.getId3v2Tag().getAlbumImage());
        }
    }

    /**
     * @param songMinData creates a song using an object of the SongMinimumData class
     *                    SongMinimumData object holds minimum data required to save a song
     */
    public Song(SongMinimumData songMinData) {
        this(songMinData.getFileAddress());
        this.setPauseLocation(songMinData.getPauseLocation());
        this.setLastPlayed(songMinData.getLastPlayed());
        this.timeStampLastPlayed = songMinData.getTimeStampLastPlayed();
        this.isFavorite = songMinData.isFavorite();
    }

    /**
     * renews the this object with it's initial values
     */
    public void reNewSong() {
        try {
            this.fis = new FileInputStream(this.address);
            this.mp3 = new Mp3File(address);
            this.player = new AdvancedPlayer(fis);
            this.totalSongLength = fis.available();
        } catch (IOException | UnsupportedTagException | InvalidDataException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return an Artwork object
     * read the Artwork class docs for more info
     */
    public Artwork getArtwork() {
        Artwork a = new Artwork(this);
        a.setTimeStampLastPlayed(this.timeStampLastPlayed);
        return a;
    }

    public String getArtist() {
        return super.getCaption();
    }

    public String getAlbum() {
        return album;
    }

    public AdvancedPlayer getPlayer() {
        return player;
    }

    public long getTimeInSecond() {
        return timeInSecond;
    }

    public long getTotalSongLength() {
        return totalSongLength;
    }

    public SongMinimumData getSongMinimumData() {
        return new SongMinimumData(address, lastPlayed, pauseLocation, timeStampLastPlayed, isFavorite);
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }

    public long getPauseLocation() {
        return pauseLocation;
    }

    public void setPauseLocation(long pauseLocation) {
        this.pauseLocation = pauseLocation;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }
}
