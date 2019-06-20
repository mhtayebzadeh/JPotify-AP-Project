package JPotifyLogic;

import JPotifyLogic.Entity.Song;

public class LogicTest {
    public static void main(String[] args) {
        Song song = new Song("src\\JPotifyLogic\\Songs\\Piano Concerto No 5 in E flat major.mp3");
        song.play();
        System.out.println(song.getTitle());
        System.out.println(song.getAlbum());
        System.out.println(song.getArtist());
        System.out.println(song.getLastPlayed());
        song.stop();
    }
}
