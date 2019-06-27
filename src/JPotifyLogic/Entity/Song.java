package JPotifyLogic.Entity;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Song extends Entity implements Serializable {
    private String address;
    private String album;
    private String lastPlayed;
    private long timeStampLastPlayed;
    private boolean repeat;
    private boolean paused;
    private long pauseLocation;
    private long totalSongLength;
    private FileInputStream fis;
    private boolean isFavorite;

    private Mp3File mp3;
    private AdvancedPlayer player;


    public Song(String address) {
        this.address = address;

        try {
            this.fis = new FileInputStream(this.address);
            this.mp3 = new Mp3File(address);
            this.player = new AdvancedPlayer(this.fis);
            this.totalSongLength = this.fis.available();
            this.isFavorite = false;
        } catch (IOException | UnsupportedTagException | InvalidDataException | JavaLayerException e) {
            e.printStackTrace();
        }


        // TODO: bonus not implemented
        if (this.mp3 != null) {
            this.album = this.mp3.getId3v1Tag().getAlbum();

            this.setTitle(this.mp3.getId3v1Tag().getTitle());
            this.setCaption(this.mp3.getId3v1Tag().getArtist());
            this.setImageData(this.mp3.getId3v2Tag().getAlbumImage());
        }
    }

    public Song(SongMinimumData songMinData) {
        this(songMinData.getFileAddress());
        this.setPauseLocation(songMinData.getPauseLocation());
        this.setLastPlayed(songMinData.getLastPlayed());
        this.timeStampLastPlayed = songMinData.getTimeStampLastPlayed();
    }

    public String getArtist() {
        return super.getCaption();
    }

    public String getAlbum() {
        return album;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public AdvancedPlayer getPlayer() {
        return player;
    }

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

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

    public boolean getPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public long getPauseLocation() {
        return pauseLocation;
    }

    public void setPauseLocation(long pauseLocation) {
        this.pauseLocation = pauseLocation;
    }

    public long getTotalSongLength() {
        return totalSongLength;
    }


    public SongMinimumData getSongMinimumData() {
        return new SongMinimumData(address, lastPlayed, pauseLocation, timeStampLastPlayed);
    }

    public long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Artwork getArtwork() {
        return new Artwork(this);
    }
}
