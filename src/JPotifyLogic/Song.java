package JPotifyLogic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class Song {
    private String address;
    private String title;
    private String artist;
    private String album;
    private byte[] imageData;
    private Mp3File mp3;
    private AdvancedPlayer player;

    public Song(String address) {
        this.address = address;
        FileInputStream fis;
        try {
            fis = new FileInputStream(this.address);
            this.mp3 = new Mp3File(address);
            this.player = new AdvancedPlayer(fis);
        } catch (IOException | UnsupportedTagException | InvalidDataException | JavaLayerException e) {
            e.printStackTrace();
        }

        // TODO: bonus not implemented
        this.title = this.mp3.getId3v1Tag().getTitle();
        this.artist = this.mp3.getId3v1Tag().getArtist();
        this.album = this.mp3.getId3v1Tag().getAlbum();
        this.imageData = this.mp3.getId3v2Tag().getAlbumImage();
    }

    public void play() {
        try {
            this.player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void stop() { this.player.stop(); }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
