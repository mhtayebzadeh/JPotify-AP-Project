package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.PlayListMinData;
import JPotifyLogic.Playlist.Playlist;

import java.io.*;
import java.util.ArrayList;

public class FileManager implements Serializable {
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<SongMinimumData> songsMinData = new ArrayList<>();
    private ArrayList<PlayListMinData> playListsMinData = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private ArrayList<Playlist> albums = new ArrayList<>();
    private ArrayList<Playlist> artists = new ArrayList<>();
    private String defaultSaveDir = "savedData";

    public FileManager() {

    }

    //TODO: Load date
    public void loadData(String dataDirectory) {
        try {

            FileInputStream fis = new FileInputStream(dataDirectory + "\\" + "songs.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.songsMinData = (ArrayList<SongMinimumData>) ois.readObject();
            ois.close();

            this.songs = new ArrayList<>();
            for (SongMinimumData s : songsMinData)
                this.songs.add(new Song(s));

            fis = new FileInputStream(dataDirectory + "\\" + "playlists.ser");
            ois = new ObjectInputStream(fis);
            this.playListsMinData = ((ArrayList<PlayListMinData>) ois.readObject());
            ois.close();

            this.playlists = new ArrayList<>();
            for (PlayListMinData p : playListsMinData)
                playlists.add(new Playlist(p));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadData() {
        loadData(this.defaultSaveDir);
    }

    //TODO: Save last data
    public void saveData(String dataDirectory) {
        try {
            this.songsMinData = new ArrayList<>();
            for (Song s : songs)
                this.songsMinData.add(s.getSongMinimumData());

            FileOutputStream fos = new FileOutputStream(dataDirectory + "\\" + "songs.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.songsMinData);
            oos.close();


            this.playListsMinData = new ArrayList<>();
            for (Playlist p : this.playlists)
                this.playListsMinData.add(p.getPlayListMinData());
            fos = new FileOutputStream(dataDirectory + "\\" + "playlists.ser");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this.playListsMinData);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveData() {
        saveData(this.defaultSaveDir);
    }

    public void add2Songs(Song song) {
        this.songs.add(song);
    }

    public void add2PlayLists(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    public void updateArtists()
    {
        ArrayList<String> artistName = new ArrayList<>();
        for(Song s : songs)
            if(!artistName.contains(s.getArtist()))
                artistName.add(s.getArtist());

        Artist a;
        for(String artist_ : artistName)
        {
            a = new Artist(artist_);
            for(Song s:songs)
                if(s.getArtist().equals(artist_))
                    a.addSong(s);

            artists.add(a);
        }
    }

    public void updateAlbums()
    {
        ArrayList<String> albumsName = new ArrayList<String>();
        for(Song s : songs)
            if(!albumsName.contains(s.getAlbum()))
                albumsName.add(s.getAlbum());

        Album a;
        for(String album_ : albumsName)
        {
            a = new Album(album_);
            for(Song s:songs)
                if(s.getAlbum().equals(album_))
                    a.addSong(s);

            albums.add(a);
        }
    }

    public ArrayList<Playlist> getAlbums() {
        return albums;
    }

    public ArrayList<Playlist> getArtists() {
        return artists;
    }
}
