package JPotifyLogic.Playlist;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends Entity implements Serializable {
    private ArrayList<Song> songs = new ArrayList<Song>();

    // TODO: caption and image not being set
    public Playlist() { this.songs = new ArrayList<Song>(); }

    public Playlist(PlayListMinData playListMinData)
    {
        songs = new ArrayList<Song>();
        setTitle(playListMinData.getTitle());
        setCaption(playListMinData.getCaption());
        setImageData(playListMinData.getImageData());
        for(SongMinimumData s : playListMinData.getSongsMinData())
            songs.add(new Song(s));
    }
    @Override
    public void setTitle(String title) { super.setTitle(title); }

    public void addSong(Song song) {this.songs.add(song); }
    public void removeSong(Song song) { this.songs.remove(song); }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public PlayListMinData getPlayListMinData()
    {
        PlayListMinData p = new PlayListMinData(this.getTitle(),this.getCaption(),this.getImageData());
        for(Song s:songs)
            p.addSongsMinData(s.getSongMinimumData());

        return p;
    }
}
