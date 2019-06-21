package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;

import java.util.concurrent.TimeUnit;

public class LogicTest {
    public static void main(String[] args) throws InterruptedException {
        Song song1 = new Song("src\\JPotifyLogic\\Songs\\Piano Concerto No 5 in E flat major.mp3");
        Song song2 = new Song("src\\JPotifyLogic\\Songs\\Mohammad_Motamedi_Koobaar.mp3");
        Playlist playlist = new Playlist();
        playlist.addSong(song2);
        playlist.addSong(song1);
        Player player = new Player("jpotify");
        player.setPlayList(playlist , song2);
//        Thread.sleep(5000);
//        player.stop_();
//        song.play();
        System.out.println(song2.getTitle());
        System.out.println(song2.getAlbum());
        System.out.println(song2.getArtist());
        System.out.println(song2.getLastPlayed());
//        song.stop();
    }
}
