package JPotifyLogic;

import JPotifyLogic.Network.Friend;
import JPotifyLogic.Network.Server;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.IOException;
import java.util.ArrayList;

public class NetworkManager {
    private static SharedPlaylist sharedPlaylist;
    private ArrayList<Friend> friendsList;

    public NetworkManager() {
        this.friendsList = new ArrayList<>();
        Server server = new Server();
        server.start(); // start server as threead ...

    }

    public static SharedPlaylist getSharedPlaylist() {
        sharedPlaylist = FileManager.getSharedPlaylist();
        return sharedPlaylist;
    }

    public void setSharedPlaylist(SharedPlaylist sharedPlaylist) {
        NetworkManager.sharedPlaylist = sharedPlaylist;
        Server.setSharedPlaylist(sharedPlaylist);
    }

    public void addFriend(Friend friend) {
        for (Friend f : this.friendsList)
            if (f.getName().equals(friend.getName()))
                return;
        this.friendsList.add(friend);
    }

//    public void setSharedPlaylist(SharedPlaylist sharedPlaylist_) {
//        sharedPlaylist = sharedPlaylist_;
//        this.server.setSharedPlaylist(sharedPlaylist_);  // not work because of thread
//        Server.setSharedPlaylist(sharedPlaylist_); // not work because of thread
//
//    }

    public ArrayList<Friend> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<Friend> friendsList) {
        this.friendsList = friendsList;
    }

    public void updateSharedPlaylistFromFileManager() {
        sharedPlaylist = FileManager.getSharedPlaylist();
    }

    public void updateFriendsLastSong() {
        for (Friend f : friendsList) {
            try {
                f.updateLastArtwork();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}