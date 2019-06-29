package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;
import JPotifyLogic.Playlist.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * most crucial class as it collects the required fields from Logic
 * to be passed to GUI and used in it and its various components
 */
public class FileManager implements Serializable {
    private static SharedPlaylist sharedPlaylist;
    private static ArrayList<Song> songs;
    private final String defaultSaveDir = "savedData";
    private FavoritePlaylist favoritePlaylist;
    private ArrayList<Playlist> playlists;
    private ArrayList<Album> albums;
    private ArrayList<Artist> artists;
    private ArrayList<SongMinimumData> songsMinData;
    private ArrayList<PlaylistMinData> playListsMinData;

    /**
     * default constructor
     */
    public FileManager() {
        sharedPlaylist = new SharedPlaylist();
        this.favoritePlaylist = new FavoritePlaylist();
        this.songs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.albums = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.songsMinData = new ArrayList<>();
        this.playListsMinData = new ArrayList<>();
    }

    //TODO: maybe better to move to Playlist class?! without param or return, just void void. Add Javadoc
    private static void sortByLastPlayed(Playlist playlist) {
        ArrayList<Song> newSongs = new ArrayList<>();
        ArrayList<Song> temp = (ArrayList<Song>) playlist.getSongs().clone();
        Song last;
        if (playlist.getSongs().size() == 0)
            return;

        for (Song ignored : playlist.getSongs()) {
            last = temp.get(0);
            for (Song s : temp) {
                if (last.getTimeStampLastPlayed() < s.getTimeStampLastPlayed())
                    last = s;
            }
            newSongs.add(last);
            temp.remove(last);
        }
        playlist.setSongs(newSongs);
    }

    public static SharedPlaylist getSharedPlaylist() {
        return sharedPlaylist;
    }

    public static void setSharedPlaylist(SharedPlaylist sharedPlaylist_) {
        sharedPlaylist = sharedPlaylist_;
    }

    //TODO: just search in songs, search in playlist should be added, Add Javadoc
    public static Playlist searchInSongs(String searchString) {
        Playlist p = new Playlist();
        p.setTypeOfPlaylist("search");
        p.setTitle("Search Playlist");
        for (Song s : songs) {
            if (s.getTitle().contains(searchString) || s.getCaption().contains(searchString))
                p.addSong(s);
        }
        return p;
    }

    private void sortAllPlayLists() {
        for (Playlist p : playlists)
            sortByLastPlayed(p);
        for (Playlist p : albums)
            sortByLastPlayed(p);
        for (Playlist p : artists)
            sortByLastPlayed(p);
        sortByLastPlayed(favoritePlaylist);
        sortByLastPlayed(sharedPlaylist);
    }

    /**
     * @param dataDirectory loads the data from this given directory to the program
     */
    private void loadData(String dataDirectory) {
        try {
            FileInputStream fis = new FileInputStream(Paths.get(dataDirectory, "songs.ser").toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(Paths.get(dataDirectory, "songs.ser").toString());
            this.songsMinData = (ArrayList<SongMinimumData>) ois.readObject();
            ois.close();

            songs = new ArrayList<>();
            for (SongMinimumData s : this.songsMinData)
                songs.add(new Song(s));

            fis = new FileInputStream(Paths.get(dataDirectory, "playlists.ser").toString());
            ois = new ObjectInputStream(fis);
            this.playListsMinData = ((ArrayList<PlaylistMinData>) ois.readObject());
            ois.close();

            this.playlists = new ArrayList<>();
            for (PlaylistMinData p : this.playListsMinData)
                this.playlists.add(new Playlist(p));


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.update();
    }

    /**
     * loads the data from the defaultSaveDir to the program
     */
    public void loadData() {
        loadData(this.defaultSaveDir);
    }

    /**
     * @param dataDirectory saves the program's current data to the given directory
     */
    private void saveData(String dataDirectory) {
        File theDir = new File(dataDirectory);
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (Exception ignored) {
            }
        }
        try {
            this.songsMinData = new ArrayList<>();
            for (Song s : songs)
                this.songsMinData.add(s.getSongMinimumData());

            FileOutputStream fos = new FileOutputStream(Paths.get(dataDirectory, "songs.ser").toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.songsMinData);
            oos.close();


            this.playListsMinData = new ArrayList<>();
            for (Playlist p : this.playlists)
                this.playListsMinData.add(p.getPlayListMinData());
            fos = new FileOutputStream(Paths.get(dataDirectory, "playlists.ser").toString());
            oos = new ObjectOutputStream(fos);

            oos.writeObject(this.playListsMinData);
            oos.close();

//            PlaylistMinData sharedPlaylistMinData = sharedPlaylist.getPlayListMinData();
//            fos = new FileOutputStream(Paths.get(dataDirectory, "sharedPlaylist.ser").toString());
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(sharedPlaylistMinData);
//            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * saves program's current data to the defaultSaveDir
     */
    public void saveData() {
        saveData(this.defaultSaveDir);
    }

    /**
     * searches the playlists list for the desired playlist
     *
     * @param name name a playlist
     * @return the found playlist. null if not found
     */
    public Playlist getPlaylistFromName(String name) {
        for (Playlist playlist : this.playlists)
            if (playlist.getTitle().equals(name))
                return playlist;
        return null;
    }

    /**
     * a wrapper for all 3 kinds of updates which are implemented here
     * this method is used in GUI when needed instead if calling each update separately
     */
    public void update() {
        this.updateAlbums();
        this.updateArtists();
        this.updateFavorite();
        this.updateShared();
        this.sortAllPlayLists();
    }

    /**
     * updates the albums list whenever a new song is added
     */
    private void updateAlbums() {
        this.albums = new ArrayList<>();
        ArrayList<String> albumNames = new ArrayList<>();

        for (Song s : songs)
            if (!albumNames.contains(s.getAlbum()))
                albumNames.add(s.getAlbum());

        for (String albumName : albumNames) {
            Album a = new Album(albumName);
            a.setTitle(albumName);
            for (Song s : songs)
                if (s.getAlbum().equals(albumName)) {
                    a.addSong(s);
                    a.setImageData(s.getImageData());
                    a.setCaption(s.getCaption());
                }
            this.albums.add(a);
        }
    }

    /**
     * updates the artists list whenever a new song is added
     */
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

    /**
     * updates the favorite playlist object using the songs isFavorite boolean field
     * this method is called in the GUI where a song is liked using the heart button
     */
    private void updateFavorite() {
        this.favoritePlaylist = new FavoritePlaylist();
        for (Song s : songs)
            if (s.isFavorite())
                this.favoritePlaylist.addSong(s);
    }

    private void updateShared() {
        sharedPlaylist = new SharedPlaylist();
        for (Song s : songs)
            if (s.isShared())
                sharedPlaylist.addSong(s);
    }

    /**
     * @param song add a song to the whole library if it hasn't been added already
     */
    public void add2Songs(Song song) {
        for (Song s : songs)
            if (song.getTitle().equals(s.getTitle()))
                return;
        songs.add(song);
    }

    /**
     * @param playlist add a playlist to the playlists if it hasn't been added already
     */
    public void add2PlayLists(Playlist playlist) {
        for (Playlist p : this.playlists)
            if (playlist.getTitle().equals(p.getTitle()))
                return;
        this.playlists.add(playlist);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
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

    public void setFavoritePlaylist(FavoritePlaylist favoritePlaylist) {
        this.favoritePlaylist = favoritePlaylist;
    }
}
