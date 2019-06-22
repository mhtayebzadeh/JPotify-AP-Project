package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;

import java.util.ArrayList;

public class LogicData {
    private ArrayList<Song> songs;
    private ArrayList<Playlist> playlists;

    public LogicData() {
        this.songs = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }
}
