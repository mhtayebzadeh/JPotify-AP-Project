package JPotifyLogic.Playlist;

import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Playlist class contains a list of songs which can be added to it
 * or automatically added (read more in child classes docs)
 */
public class Playlist extends Entity implements Serializable {
    private ArrayList<Song> songs;
    private String typeOfPlaylist = "normal";

    /**
     * default constructor
     */
    public Playlist() {
        this("");
    }

    /**
     * @param name name of the playlist
     */
    public Playlist(String name) {
        this.setTitle(name);
        this.songs = new ArrayList<>();
    }

    /**
     * @param playListMinData creates a playlist using an object of the PlaylistMinimumData class
     *                        SongMinimumData object holds minimum data required to save a song
     */
    public Playlist(PlaylistMinData playListMinData) {
        songs = new ArrayList<>();
        setTitle(playListMinData.getTitle());
        setCaption(playListMinData.getCaption());
        setImageData(playListMinData.getImageData());
        for (SongMinimumData s : playListMinData.getSongsMinData())
            songs.add(new Song(s));
        setTypeOfPlaylist(playListMinData.getTypeOfPlaylist());
    }

    /**
     * @param song adds the song to this playlist
     */
    public void addSong(Song song) {
        for (Song s : this.songs)
            if (s.equals(song))
                return;
        this.songs.add(song);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    /**
     * @return playlistMinData contains the minimum data required to save a playlist
     */
    public PlaylistMinData getPlayListMinData() {
        PlaylistMinData p = new PlaylistMinData(this.getTitle(),
                this.getCaption(), this.getImageData(), this.typeOfPlaylist);
        for (Song s : this.songs)
            p.addSongsMinData(s.getSongMinimumData());
        return p;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setTypeOfPlaylist(String typeOfPlaylist) {
        this.typeOfPlaylist = typeOfPlaylist;
    }
}
