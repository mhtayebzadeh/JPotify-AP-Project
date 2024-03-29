package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Player extends Thread {
    private static Song song;
    private static FileInputStream _fis_;
    private static Playlist playlist;
    private static boolean repeat;
    private static boolean shuffle;
    private static Thread playThread;
    private static boolean playing;

    public Player(String playerName) {
        super(playerName);
    }

    public static boolean isPlaying() {
        return playing;
    }

    private static void setPlaying(boolean playing) {
        Player.playing = playing;
    }

    private static void setCurrentSong(Song song_) {
        song = song_;
    }

    private static void setCurrent_fis_(FileInputStream fis) {
        _fis_ = fis;
    }

    public static boolean isRepeat() {
        return repeat;
    }

    public static void setRepeat(boolean repeat) {
        Player.repeat = repeat;
    }

    public static void setShuffle(boolean shuffle) {
        Player.shuffle = shuffle;
    }

    public static void setCurrentPlaylist(Playlist playlist_) {
        playlist = playlist_;
    }
    // start music from first of lib

    public static long getElapsedTimeInSecond() {
        return song.getTimeInSecond() - getRemainTimeInSecond();
    }

    public static int getTotalTimeInSecond() {
        return (int) song.getTimeInSecond();
    }

    public static long getRemainTimeInSecond() {
        try {
            return (long) ((float) song.getTimeInSecond() * (((float) _fis_.available()) / ((float) song.getTotalSongLength())));
        } catch (IOException e) {
//            e.printStackTrace();
            return 0;
        }
    }

    public static void forceStop() {
        if (playThread != null) {
            playThread.stop();
            setPlaying(false);
        }
    }

    public static float getElapsedTimeInPercent() {
        try {
            return 100 - (float) (100.0 * (((float) _fis_.available()) / ((float) song.getTotalSongLength())));
        } catch (IOException e) {
//            e.printStackTrace();
            return 0;
        }
    }

    public static Playlist getPlaylist() {
        return playlist;
    }

    // start music from first of lib
    public void setPlayList(Playlist playlist) {
        //TODO:
        try {
            this.setPlayListAndSong(playlist, playlist.getSongs().get(0));
        } catch (Exception ignored) {
        }
    }

    private void setPlayListAndSong(Playlist playlist_, Song song) {
        Iterator<Song> it = playlist_.getSongs().iterator();
        playlist = playlist_;
        while (it.next() != song) ;
        Player.song = song;
        this.stop_();
        MyRunnable myRunnable = new MyRunnable(song);
        myRunnable.setIterator(it); // add library iter to myRunnable
        playThread = new Thread(myRunnable);
        playThread.start();
        //TODO: lastPlayed of all library songs not set
    }

    public void setShufflePlaylistAndSong(Playlist playlist_) {
        if (playlist_ != null)
            if (playlist_.getSongs().size() > 0)
                setShufflePlaylistAndSong(playlist_, playlist_.getSongs().get(0));
    }

    public void setShufflePlaylistAndSong(Playlist playlist_, Song song_) {
        Playlist shuffle = new Playlist("shuffle");
        ArrayList<Song> songsArr = (ArrayList<Song>) playlist_.getSongs().clone();
        Random randomGenerator = new Random();
        int r = 0;
        for (Song s : playlist_.getSongs()) {
            r = randomGenerator.nextInt(songsArr.size());
            shuffle.addSong(songsArr.get(r));
            songsArr.remove(songsArr.get(r));
        }
        setPlayListAndSong(shuffle, song_);
    }

    //TODO: setShuffle this.playlist and this.song AND pause and resume
    public void setShuffle() {
        float t = getElapsedTimeInPercent();
        setShufflePlaylistAndSong(playlist, song);
        gotoPercent(t);
    }

    public void nextSong() {
        if (playlist == null || song == null)
            return;

        Iterator<Song> iter = playlist.getSongs().iterator();
        while (iter.hasNext())
            if (iter.next().equals(song))
                if (iter.hasNext())
                    setPlayListAndSong(playlist, iter.next());
    }

    public void previousSong() {
        if (playlist == null || song == null)
            return;
        if (getElapsedTimeInSecond() > 4)
            setPlayListAndSong(playlist, song);
        else {
            Song last = null;
            for (Song s : playlist.getSongs()) {
                if (s.equals(song))
                    break;
                last = s;
            }
            if (last != null)
                setPlayListAndSong(playlist, last);
        }
    }

    public void setPlayListAndSongAndSkip(Playlist playlist_, Song song_, long skip) {
        Iterator<Song> it = playlist_.getSongs().iterator();
        playlist = playlist_;
        while (it.next() != song) ;
        song = song_;
        this.stop_();
        MyRunnable myRunnable = new MyRunnable(song, skip);
        myRunnable.setIterator(it); // add library iter to myRunnable
        playThread = new Thread(myRunnable);
        playThread.start();
        //TODO: lastPlayed of all library songs not set
    }

    public void setSongAndSkip(Song song_, long skip) {
        song = song_;
        this.stop_();
        playThread = new Thread(new MyRunnable(song_, skip));
        playThread.start();
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song_) {
        setSongAndSkip(song_, 0);
    }

    // TODO: using deprecated thread stop method
    public void stop_() {
        if (playThread != null) {
            playThread.stop();
            setPlaying(false);
        }
    }

    public void resume_() {
        song.setPaused(false);
        try {
            long skip = song.getTotalSongLength() - song.getPauseLocation();
            if (playlist != null && playlist.getSongs().contains(song))
                setPlayListAndSongAndSkip(playlist, song, skip);
            else
                setSongAndSkip(song, skip);

        } catch (Exception e) {
        }
    }

    public void pause() {
        song.setPaused(true);
        try {
            song.setPauseLocation(song.getFis().available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stop_();
        setPlaying(false);
    }

    public void gotoSecond(long sec) {
        gotoPercent((float) (100.0 * (float) sec / (float) song.getTimeInSecond()));
    }

    public void gotoPercent(float percent) {
        long skip = (long) ((percent / 100) * song.getTotalSongLength());
        if (song.isPaused()) {
            song.setPauseLocation(skip);
        } else {
            try {
                if (playlist != null && playlist.getSongs().contains(song))
                    setPlayListAndSongAndSkip(playlist, song, skip);
                else
                    setSongAndSkip(song, skip);
            } catch (Exception ignored) {
            }
        }
    }

    public void run() {
        while (true) {
            //TODO:
        }
    }

    //TODO: play at time ...
    private class MyRunnable implements Runnable {
        private AdvancedPlayer player;
        private FileInputStream fis;
        private Song song;
        private Iterator<Song> shuffleIter;
        private Playlist shulePlaylist = null;
        private Iterator<Song> it = null;

        public MyRunnable(Song song) {
            if (!song.isPaused())
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

        public Song selectNextSong() {
            if (shuffle) {
                if (shulePlaylist == null) {
                    Playlist shuffleP = new Playlist("shuffle");
                    Playlist playlist_ = Player.getPlaylist();
                    ArrayList<Song> songsArr = (ArrayList<Song>) playlist_.getSongs().clone();
                    Random randomGenerator = new Random();
                    int r = 0;
                    for (Song s : playlist_.getSongs()) {
                        r = randomGenerator.nextInt(songsArr.size());
                        shuffleP.addSong(songsArr.get(r));
                        songsArr.remove(songsArr.get(r));
                    }
                    shuffleP.removeSong(this.song);
                    shulePlaylist = shuffleP;
                    shuffleIter = shulePlaylist.getSongs().iterator();
                }

                if (shuffleIter.hasNext()) {
                    Song s = shuffleIter.next();
                    s.reNewSong();
                    return s;
                }


                return null;

            } else {
                if (it != null) {
                    if (it.hasNext()) {
                        this.song = it.next();
                        if (!this.song.isPaused()) {
                            this.song.reNewSong();
                        }
                        return this.song;
                    }
                }

                return null;
            }
        }

        @Override
        public void run() {
            try {
                this.setLastPlayed(this.song);
                Player.setCurrentSong(song);
                Player.setPlaying(true);
                this.player.play();
                while (isRepeat()) {
                    this.song.reNewSong();
                    this.player = new AdvancedPlayer(this.song.getFis());
                    Player.setCurrentSong(song);
                    Player.setPlaying(true);
                    this.player.play();
                }

                while (true) {
                    Song newSong = selectNextSong();
                    if (newSong == null)
                        break;
                    try {
                        this.song = newSong;
                        FileInputStream f = this.song.getFis();
                        this.player = new AdvancedPlayer(f);
                        this.setLastPlayed(this.song);
                        Player.setCurrentSong(song);
                        Player.setCurrent_fis_(f);
                        this.player.play();
                    } catch (Exception ignored) {
                    }
                }

//                if(repeat == true ) // repeat song
                Player.setPlaying(false);

            } catch (JavaLayerException e) {
                e.printStackTrace();
//                this.player.stop();
                Player.forceStop();
            }
        }
    }
}
