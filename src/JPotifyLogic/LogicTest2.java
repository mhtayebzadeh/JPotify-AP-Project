package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Network.Friend;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.IOException;

public class LogicTest2 {
    public static void main(String[] args) throws InterruptedException, IOException {

        Player player = new Player("jpotify");
        FileManager fileManager = new FileManager();
        fileManager.loadData();
        NetworkManager networkManager = new NetworkManager();
        //Friend f1 = new Friend("local");
        Friend f2 = new Friend("tayeb" , "172.21.208.173");

        if(fileManager.getPlaylists() != null)
        {
            SharedPlaylist sharedPlaylist = new SharedPlaylist();
            sharedPlaylist.addSong(fileManager.getSongs().get(0));
            sharedPlaylist.addSong(fileManager.getSongs().get(fileManager.getSongs().size() - 1));
            fileManager.setSharedPlaylist(sharedPlaylist);
            networkManager.setSharedPlaylist(fileManager.getSharedPlaylist());
        }

        //networkManager.addFriend(f1);
        networkManager.addFriend(f2);
        while (true) {
            Thread.sleep(2000);
            networkManager.updateFriendsLastSong();
        }

    }
}
