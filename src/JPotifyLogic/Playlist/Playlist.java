package JPotifyLogic.Playlist;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;

import java.util.ArrayList;

public class Playlist extends Entity {
    private ArrayList<Song> songs;

    // TODO: caption and image not being set
    public Playlist() { this.songs = new ArrayList<>(); }

    @Override
    public void setTitle(String title) { super.setTitle(title); }

    public void addSong(Song song) {this.songs.add(song); }
    public void removeSong(Song song) { this.songs.remove(song); }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
