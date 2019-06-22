package JPotifyLogic.Entity;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalTime;

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

    private Mp3File mp3;
    private AdvancedPlayer player;


    public Song(String address) {
        this.address = address;

        try {
            fis = new FileInputStream(this.address);
            this.mp3 = new Mp3File(address);
            this.player = new AdvancedPlayer(fis);
            this.totalSongLength = fis.available();
        } catch (IOException | UnsupportedTagException | InvalidDataException | JavaLayerException e) {
            e.printStackTrace();
        }



        // TODO: bonus not implemented
        this.album = this.mp3.getId3v1Tag().getAlbum();

        this.setTitle(this.mp3.getId3v1Tag().getTitle());
        this.setCaption(this.mp3.getId3v1Tag().getArtist());
        this.setImageData(this.mp3.getId3v2Tag().getAlbumImage());
    }
    public Song(SongMinimumData songMinData)
    {
        this(songMinData.getFileAddress());
        this.setPauseLocation(songMinData.getPauseLocation());
        this.setLastPlayed(songMinData.getLastPlayed());
        this.timeStampLastPlayed = songMinData.getTimeStampLastPlayed();
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
    public String getArtist() { return super.getCaption(); }
    public String getAlbum() { return album; }
    public String getLastPlayed() { return lastPlayed; }

    public AdvancedPlayer getPlayer() {
        return player;
    }

    public FileInputStream getFis() {
        try {
            fis = new FileInputStream(this.address);
            this.mp3 = new Mp3File(address);
            this.player = new AdvancedPlayer(fis);
            this.totalSongLength = fis.available();
        } catch (IOException | UnsupportedTagException | InvalidDataException | JavaLayerException e) {
            e.printStackTrace();
        }
        return fis;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    public boolean getPaused()
    {
        return paused;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
    public boolean getRepeat()
    {return repeat; }

    public long getPauseLocation() {
        return pauseLocation;
    }

    public void setPauseLocation(long pauseLocation) {
        this.pauseLocation = pauseLocation;
    }

    public long getTotalSongLength() {
        return totalSongLength;
    }

    public SongMinimumData getSongMinimumData()
    {
        return new SongMinimumData(address,lastPlayed,pauseLocation,timeStampLastPlayed);
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }

    public long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }
}
