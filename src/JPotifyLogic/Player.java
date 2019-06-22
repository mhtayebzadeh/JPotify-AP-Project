package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Iterator;

public class Player extends Thread {
    private String name;
    private Song song = null;
    private Thread playThread = null;

    public Player(String playerName) {
        super(playerName);
        name = playerName;
    }

    // start music from first of lib
    public void setPlayList(Playlist playlist) {
        //TODO:
    }

    // start music from first of lib
    public void setPlayList(Playlist playlist, Song song) {
        Iterator<Song> it = playlist.getSongs().iterator();
        while (it.next() != song) ;
        this.song = song;
        this.stop_();
        MyRunnable myRunnable = new MyRunnable(song);
        myRunnable.setIterator(it); // add library iter to myRunnable
        this.playThread = new Thread(myRunnable);
        this.playThread.start();
        //TODO: lastPlayed of all library songs not set
    }

    public void setSong(Song song) {
        this.song = song;
        this.stop_();
        this.playThread = new Thread(new MyRunnable(song));
        this.playThread.start();
    }

//    public void play() {

//    }

    // TODO: using deprecated thread stop method
    public void stop_() {
        if (playThread != null)
            this.playThread.stop();
    }

    public void resume_() {
        try {
            this.song.setPaused(false);
            long skip = this.song.getTotalSongLength() - this.song.getPauseLocation();
            this.playThread = new Thread(new MyRunnable(song, skip));
        } catch (Exception e) {
        }
    }

    public void run() {
        while (true) {
            //TODO:
        }
    }

    private class MyRunnable implements Runnable {
        private AdvancedPlayer player;
        private FileInputStream fis;
        private Song song;
        private Iterator<Song> it = null;

        public MyRunnable(Song song) {
            this.song = song;
            this.fis = song.getFis();
            this.player = song.getPlayer();
        }

        public MyRunnable(Song song, long skipFrame) {
            this.song = song;
            try {
                this.fis = song.getFis();
                fis.skip(skipFrame);
                this.player = new AdvancedPlayer(fis);
            } catch (JavaLayerException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setIterator(Iterator it) {
            this.it = it;
        }

        private void setLastPlayed(Song song) {
            int hour = LocalTime.now().getHour();
            int minute = LocalTime.now().getMinute();
            String hourStr = hour / 10 == 0 ? "0" + hour : "" + hour;
            String minuteStr = minute / 10 == 0 ? "0" + minute : "" + minute;
            song.setLastPlayed(hourStr + ":" + minuteStr);
        }

        @Override
        public void run() {
            try {
                this.setLastPlayed(this.song);
                this.player.play();
                if (it != null) {
                    while (it.hasNext()) {
                        try {
                            this.song = it.next();
                            this.player = new AdvancedPlayer(this.song.getFis());
                            this.setLastPlayed(this.song);
                            this.player.play();
                        } catch (Exception e) {
                        }
                    }
                }
//                if(repeat == true ) // repeat song


            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
}
