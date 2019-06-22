package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Library.Playlists;
import JPotifyLogic.Library.Songs;
import JPotifyLogic.Playlist.Album;
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
//        Song song3 = new Song("src\\JPotifyLogic\\Songs\\07-Ali_Zand_Vakili_Nemiayi.mp3");
//        Song song4 = new Song("src\\JPotifyLogic\\Songs\\08-Ali_Zand_Vakili_Jananeh.mp3");
//
//        Playlist playlist1 = new Playlist();
//        Playlist playlist2 = new Playlist();
//        playlist1.addSong(song1);
//        playlist1.addSong(song2);
//        playlist2.addSong(song3);
//        playlist2.addSong(song4);
//
//        fileManager.add2Songs(song1);
//        fileManager.add2Songs(song2);
//        fileManager.add2Songs(song3);
//        fileManager.add2Songs(song4);
//
//        player.setSong(song4);
//        Thread.sleep(2000);
//
//        fileManager.add2PlayLists(playlist1);
//        fileManager.add2PlayLists(playlist2);
//        fileManager.saveData();


//  Load data

        fileManager.loadData();
        fileManager.updateAlbums();
        fileManager.updateArtists();
        for(Album a : fileManager.getAlbums())
            System.out.println(a.getAlbum());
        System.out.println("-----------------");
        Song song = (Song)fileManager.getPlaylists().get(1).getSongs().get(1);


        Song song4 = song;
        System.out.println(song4.getTitle());
        System.out.println(song4.getAlbum());
        System.out.println(song4.getArtist());
        System.out.println(song4.getLastPlayed());

        player.setSong(song);
        Thread.sleep(5000);
        player.stop_();

//        song.stop();
    }
}
