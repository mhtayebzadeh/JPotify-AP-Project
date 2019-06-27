package JPotifyLogic;

import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Network.Friend;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.IOException;

public class LogicTest2 {
    public static void main(String[] args) throws InterruptedException, IOException {

        FileManager fileManager = new FileManager();
        NetworkManager networkManager = new NetworkManager();
        Player player = new Player("jpotify");
        GUI gui = new GUI(fileManager);
        System.out.println("JPotify Start ...");
        gui.setPlayer(player);

        Friend f1 = new Friend("me" , "localhost");
        networkManager.addFriend(f1);

        SharedPlaylist sharedPlaylist = new SharedPlaylist();
        sharedPlaylist.setSongs(fileManager.getSongs());

        fileManager.setSharedPlaylist(sharedPlaylist);
        System.out.println(fileManager.getSongs().get(0).getArtwork().getTitle());

        while(true)
        {
            Thread.sleep(2000);
            networkManager.updateFriendsLastSong();
            System.out.println(networkManager.getFriendsList().get(0).getLastArtwork().getTitle());
        }
    }
}
