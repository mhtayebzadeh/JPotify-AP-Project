package JPotifyLogic.Playlist;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist extends Entity implements Serializable {
    private String name;
    private ArrayList<Song> songs;
    private String typeOfPlaylist = "normal";

    // TODO: caption and image not being set
    public Playlist() {
        this("");
    }

    public Playlist(String name) {
        this.setTitle(name);
        this.songs = new ArrayList<>();
    }

    public Playlist(PlayListMinData playListMinData) {
        songs = new ArrayList<>();
        setTitle(playListMinData.getTitle());
        setCaption(playListMinData.getCaption());
        setImageData(playListMinData.getImageData());
        for (SongMinimumData s : playListMinData.getSongsMinData())
            songs.add(new Song(s));
        setTypeOfPlaylist(playListMinData.getTypeOfPlaylist());
    }

    public void addSong(Song song) {
        for (Song s : this.songs)
            if (s.equals(song))
                return;
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public PlayListMinData getPlayListMinData() {
        PlayListMinData p = new PlayListMinData(this.getTitle(), this.getCaption(), this.getImageData(), this.typeOfPlaylist);
        for (Song s : this.songs)
            p.addSongsMinData(s.getSongMinimumData());
        return p;
    }

    public String getTypeOfPlaylist() {
        return typeOfPlaylist;
    }

    public void setTypeOfPlaylist(String typeOfPlaylist) {
        this.typeOfPlaylist = typeOfPlaylist;
    }
}
