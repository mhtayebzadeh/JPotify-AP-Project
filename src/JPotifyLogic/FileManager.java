package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;
import JPotifyLogic.Playlist.PlayListMinData;
import JPotifyLogic.Playlist.Playlist;

import java.io.*;
import java.util.ArrayList;

public class FileManager implements Serializable {
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<SongMinimumData> songsMinData = new ArrayList<SongMinimumData>();
    private ArrayList<PlayListMinData> playListsMinData = new ArrayList<PlayListMinData>();
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
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
            songs = new ArrayList<Song>();
            for (SongMinimumData s : songsMinData)
                songs.add(new Song(s));

            fis = new FileInputStream(dataDirectory + "\\" + "playlists.ser");
            ois = new ObjectInputStream(fis);
            this.playListsMinData = ((ArrayList<PlayListMinData>) ois.readObject());
            ois.close();
            playlists = new ArrayList<Playlist>();
            for (PlayListMinData p : playListsMinData)
                playlists.add(new Playlist(p));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadData() {
        loadData(this.defaultSaveDir);
    }

    //TODO: Save last data
    public void saveData(String dataDirectory) {
        try {
            songsMinData = new ArrayList<SongMinimumData>();
            for (Song s : songs)
                songsMinData.add(s.getSongMinimumData());
            FileOutputStream fos = new FileOutputStream(dataDirectory + "\\" + "songs.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(songsMinData);
            oos.close();


            playListsMinData = new ArrayList<PlayListMinData>();
            for (Playlist p : playlists)
                playListsMinData.add(p.getPlayListMinData());
            fos = new FileOutputStream(dataDirectory + "\\" + "playlists.ser");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(playListsMinData);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
