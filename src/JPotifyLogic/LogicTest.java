package JPotifyLogic;

import JPotifyLogic.Entity.Song;

import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Playlist;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class LogicTest {
    public static void main(String[] args) throws InterruptedException {

        Player player = new Player("jpotify");
        FileManager fileManager = new FileManager();
//  save Data

//        Song song1 = new Song(Paths.get("src","JPotifyLogic","Songs","Piano Concerto No 5 in E flat major.mp3").toString());
//        Song song2 = new Song(Paths.get("src","JPotifyLogic","Songs","Mohammad_Motamedi_Koobaar.mp3").toString());
//        Song song3 = new Song(Paths.get("src","JPotifyLogic","Songs","07-Ali_Zand_Vakili_Nemiayi.mp3").toString());
//        Song song4 = new Song(Paths.get("src","JPotifyLogic","Songs","08-Ali_Zand_Vakili_Jananeh.mp3").toString());
//
//        Playlist playlist1 = new Playlist();
//        Playlist playlist2 = new Playlist();
//        playlist1.addSong(song1);
//        playlist1.addSong(song2);
//        playlist2.addSong(song3);
//        playlist2.addSong(song4);
////
//        fileManager.add2Songs(song1);
//        fileManager.add2Songs(song2);
//        fileManager.add2Songs(song3);
//        fileManager.add2Songs(song4);
//
//        player.setSong(song4);
//        Thread.sleep(2000);
//        player.stop_();
//        fileManager.add2PlayLists(playlist1);
//        fileManager.add2PlayLists(playlist2);
//        fileManager.saveData();


//  Load data

        fileManager.loadData();

        fileManager.update();
        player.setSong(fileManager.searchInSongs("Koobaar").getSongs().get(0));
        System.out.println(fileManager.searchInSongs("Koobaar").getSongs().get(0).getTitle());
        Thread.sleep(3000);
        player.gotoPercent(50);
        Thread.sleep(3000);
        player.gotoPercent(10);
        while (true)
        {
            Thread.sleep(500);
            System.out.println(Player.getElapsedTimeInSecond());
        }
//        Thread.sleep(1000);
//        player.stop_();

//        song.stop();
    }
}
