package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Library.Playlists;
import JPotifyLogic.Library.Songs;
import JPotifyLogic.Playlist.Playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class LogicTest {
    public static void main(String[] args) throws InterruptedException {

        Player player = new Player("jpotify");
        FileManager fileManager = new FileManager();
//  save Data
//        Song song1 = new Song("src\\JPotifyLogic\\Songs\\Piano Concerto No 5 in E flat major.mp3");
//        Song song2 = new Song("src\\JPotifyLogic\\Songs\\Mohammad_Motamedi_Koobaar.mp3");
//        Playlist playlist = new Playlist();
//        playlist.addSong(song2);
//        playlist.addSong(song1);
//        fileManager.add2Songs(song1);
//        fileManager.add2Songs(song2);
//        fileManager.add2PlayLists(playlist);
//        fileManager.saveData();


//  Load data
        fileManager.loadData();
        Song song2 = (Song)fileManager.getPlaylists().get(0).getSongs().get(1);
        player.setSong(song2);
        Thread.sleep(5000);
        player.stop_();

        System.out.println(song2.getTitle());
        System.out.println(song2.getAlbum());
        System.out.println(song2.getArtist());
        System.out.println(song2.getLastPlayed());
//        song.stop();
    }
}
