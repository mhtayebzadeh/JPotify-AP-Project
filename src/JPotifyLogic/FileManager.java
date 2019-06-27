package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;
import JPotifyLogic.Playlist.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager implements Serializable {
    private ArrayList<Song> songs;
    private ArrayList<SongMinimumData> songsMinData;
    private ArrayList<PlayListMinData> playListsMinData;
    private ArrayList<Playlist> playlists;
    private ArrayList<Album> albums;
    private ArrayList<Artist> artists;
    private final String defaultSaveDir = "savedData";
    private static SharedPlaylist sharedPlaylist;
    private FavoritePlaylist favoritePlaylist;

    public FileManager() {
        this.songs = new ArrayList<>();
        this.songsMinData = new ArrayList<>();
        this.playListsMinData = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.sharedPlaylist = new SharedPlaylist();
        this.favoritePlaylist = new FavoritePlaylist();
    }

    public static Playlist sortByLastPlayed(Playlist playlist) {
        ArrayList<Song> newSongs = new ArrayList<>();
        Song smaller;
        long last = 0;
        for (Song s_ : playlist.getSongs()) {
            smaller = s_;
            for (Song s : playlist.getSongs()) {
                if ((smaller.getTimeStampLastPlayed() > s.getTimeStampLastPlayed()) && (smaller.getTimeStampLastPlayed() > last))
                    smaller = s;
            }
            last = smaller.getTimeStampLastPlayed();
            newSongs.add(smaller);
        }

        playlist.setSongs(newSongs);
        return playlist;

    }

    public void loadData(String dataDirectory) {

        try {
            FileInputStream fis = new FileInputStream(Paths.get(dataDirectory , "songs.ser").toString() );
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(Paths.get(dataDirectory , "songs.ser").toString());
            this.songsMinData = (ArrayList<SongMinimumData>) ois.readObject();
            ois.close();

            this.songs = new ArrayList<>();
            for (SongMinimumData s : this.songsMinData)
                this.songs.add(new Song(s));

            //

            fis = new FileInputStream(Paths.get(dataDirectory , "playlists.ser").toString());
            ois = new ObjectInputStream(fis);
            this.playListsMinData = ((ArrayList<PlayListMinData>) ois.readObject());
            ois.close();

            this.playlists = new ArrayList<>();
            for (PlayListMinData p : this.playListsMinData)
                this.playlists.add(new Playlist(p));

            //
            //TODO: load SharedPlaylist and favouritPlaylist

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.update();
    }

    public void loadData() {
        loadData(this.defaultSaveDir);
    }

    public void saveData(String dataDirectory) {
        File theDir = new File(dataDirectory);
        if(!theDir.exists())
        {
            try{
                theDir.mkdir();
            } catch (Exception e) { }
        }
        try {
            this.songsMinData = new ArrayList<>();
            for (Song s : this.songs)
                this.songsMinData.add(s.getSongMinimumData());

            FileOutputStream fos = new FileOutputStream(Paths.get(dataDirectory , "songs.ser").toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.songsMinData);
            oos.close();


            this.playListsMinData = new ArrayList<>();
            for (Playlist p : this.playlists)
                this.playListsMinData.add(p.getPlayListMinData());
            fos = new FileOutputStream(Paths.get(dataDirectory , "playlists.ser").toString());
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this.playListsMinData);
            oos.close();

            //TODO: save SharedPlaylist and favouritPlaylist

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        saveData(this.defaultSaveDir);
    }

    public void add2Songs(Song song) {
        for (Song s : this.songs)
            if (song.getTitle().equals(s.getTitle()))
                return;
        this.songs.add(song);
    }

    public void add2PlayLists(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    public void update() {
        this.updateAlbums();
        this.updateArtists();
        this.updateFavorite();
    }

    private void updateFavorite() {
        favoritePlaylist = new FavoritePlaylist();
        for(Song s : this.songs);
        //TODO: update favorites

    }

    private void updateArtists() {
        this.artists = new ArrayList<>();
        ArrayList<String> artistNames = new ArrayList<>();

        for (Song s : this.songs)
            if (!artistNames.contains(s.getArtist()))
                artistNames.add(s.getArtist());

        for (String artistName : artistNames) {
            Artist a = new Artist(artistName);
            a.setTitle(artistName);
            for (Song s : this.songs)
                if (s.getArtist().equals(artistName)) {
                    a.addSong(s);
                    a.setImageData(s.getImageData());
                    a.setCaption(s.getCaption());
                }
            this.artists.add(a);
        }
    }

    private void updateAlbums() {
        this.albums = new ArrayList<>();
        ArrayList<String> albumNames = new ArrayList<>();

        for (Song s : this.songs)
            if (!albumNames.contains(s.getAlbum()))
                albumNames.add(s.getAlbum());

        for (String albumName : albumNames) {
            Album a = new Album(albumName);
            a.setTitle(albumName);
            for (Song s : this.songs)
                if (s.getAlbum().equals(albumName)) {
                    a.addSong(s);
                    a.setImageData(s.getImageData());
                    a.setCaption(s.getCaption());
                }
            this.albums.add(a);
        }
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public FavoritePlaylist getFavoritePlaylist() {
        return favoritePlaylist;
    }

    public static SharedPlaylist getSharedPlaylist() {
        return sharedPlaylist;
    }

    public static void setSharedPlaylist(SharedPlaylist sharedPlaylist_) {
        sharedPlaylist = sharedPlaylist_;
    }

    public void setFavoritePlaylist(FavoritePlaylist favoritePlaylist) {
        this.favoritePlaylist = favoritePlaylist;
    }
}
