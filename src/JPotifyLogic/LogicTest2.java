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
        Friend f1 = new Friend("local");
        Friend f2 = new Friend("hasan" , "172.20.0.1");

        if(fileManager.getPlaylists() != null)
        {
            fileManager.setSharedPlaylist(new SharedPlaylist(fileManager.getPlaylists().get(0)));
            networkManager.setSharedPlaylist(fileManager.getSharedPlaylist());
        }

        networkManager.addFriend(f1);
        networkManager.addFriend(f2);
        networkManager.updateFriendsLastSong();

    }
}
