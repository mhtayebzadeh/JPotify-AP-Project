package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;
import JPotifyLogic.Playlist.PlayListMinData;
import JPotifyLogic.Playlist.Playlist;

import java.io.*;
import java.util.ArrayList;

public class FileManager implements Serializable {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<SongMinimumData> songsMinData = new ArrayList<>();
    private ArrayList<PlayListMinData> playListsMinData = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();
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
}
