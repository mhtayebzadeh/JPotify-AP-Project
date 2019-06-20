package JPotifyLogic.Entity;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;

public class Song extends Entity {
    private String address;
    private String title;
    private String artist;
    private String album;
    private byte[] imageData;
    private String lastPlayed;

    private Mp3File mp3;
    private AdvancedPlayer player;
    private Thread playThread;

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
        this.album = this.mp3.getId3v1Tag().getAlbum();
        this.playThread = new Thread(new MyRunnable(this.player));

        this.setTitle(this.mp3.getId3v1Tag().getTitle());
        this.setCaption(this.mp3.getId3v1Tag().getArtist());
        this.setImageData(this.mp3.getId3v2Tag().getAlbumImage());
    }

    public void play() {
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        String hourStr = hour / 10 == 0 ? "0" + hour : "" + hour;
        String minuteStr = minute / 10 == 0 ? "0" + minute : "" + minute;
        this.lastPlayed = hourStr + ":" + minuteStr;
        this.playThread.start();
    }

    // TODO: using deprecated thread stop method
    public void stop() {
        this.playThread.stop();
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public byte[] getImageData() { return imageData; }
    public String getLastPlayed() { return lastPlayed; }

    private class MyRunnable implements Runnable {
        private AdvancedPlayer player;

        public MyRunnable(AdvancedPlayer player) {
            this.player = player;
        }

        @Override
        public void run() {
            try {
                this.player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
}
