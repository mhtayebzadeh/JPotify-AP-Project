package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Iterator;

public class Player extends Thread {
    private String name;
    private static Song song;
    private Thread playThread;
    private static FileInputStream _fis_;
    public Player(String playerName) {
        super(playerName);
        this.name = playerName;
    }

    public static void setCurrentSong(Song song_)
    {
        song = song_;
    }
    // start music from first of lib
    public void setPlayList(Playlist playlist) {
        //TODO:
        this.setPlayList(playlist, playlist.getSongs().get(0));
    }
    public static void setCurrent_fis_(FileInputStream fis)
    {
        _fis_ = fis;
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

    public Song getSong() {
        return song;
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
        if (playThread != null) {

            this.playThread.stop();

        }
    }

    public void resume_() {
        song.setPaused(false);
        try {
            this.song.setPaused(false);
            long skip = this.song.getTotalSongLength() - this.song.getPauseLocation();
            this.playThread = new Thread(new MyRunnable(song, skip));
        } catch (Exception e) {
        }
    }

    public void pause()
    {
        this.song.setPaused(true);
        this.stop_();
    }
    public void run() {
        while (true) {
            //TODO:
        }
    }

    public static long getRemainTimeInSecond()
    {
        try {
            return (long)((float)song.getTimeInSecond()*(((float)_fis_.available())/((float)song.getTotalSongLength())) );
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getElapsedTimeInSecond()
    {
        return song.getTimeInSecond() - getRemainTimeInSecond();
    }
    public static float getElapsedTimeInPercent()
    {
        try {
            return (float)(100.0*(((float)_fis_.available())/((float)song.getTotalSongLength())) );
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

//    public void goto
    //TODO: play at time ...

    private class MyRunnable implements Runnable {
        private AdvancedPlayer player;
        private FileInputStream fis;
        private Song song;
        private Iterator<Song> it = null;

        public MyRunnable(Song song) {
            if(!song.getPaused())
                song.reNewSong();
            this.song = song;
            this.fis = song.getFis();
            Player.setCurrent_fis_(this.fis);
            this.player = song.getPlayer();

        }

        public MyRunnable(Song song, long skipFrame) {
            song.reNewSong();
            this.song = song;
            try {
                this.fis = song.getFis();
                fis.skip(skipFrame);
                this.player = new AdvancedPlayer(fis);
                Player.setCurrent_fis_(this.fis);
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
            Instant instant = Instant.now();
            long timeStampMillis = instant.toEpochMilli();
            song.setTimeStampLastPlayed(timeStampMillis);
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
                            if(! this.song.getPaused())
                                this.song.reNewSong();
                            FileInputStream f = this.song.getFis();
                            this.player = new AdvancedPlayer(f);
                            this.setLastPlayed(this.song);
                            Player.setCurrentSong(song);
                            Player.setCurrent_fis_(f);
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
