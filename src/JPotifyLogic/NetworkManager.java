package JPotifyLogic;

import JPotifyLogic.Network.Friend;
import JPotifyLogic.Network.Server;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.IOException;
import java.util.ArrayList;

public class NetworkManager {
    private ArrayList<Friend> friendsList;
    private SharedPlaylist sharedPlaylist;
    private Server server;
    public NetworkManager()
    {
        this.friendsList = new ArrayList<>();
        this.server = new Server();
        server.start(); // start server as threead ...

    }
    public void addFriend(Friend friend)
    {
        friendsList.add(friend);
    }
    public ArrayList<Friend> getFriendsList() {
        return friendsList;
    }
    public void setFriendsList(ArrayList<Friend> friendsList) {
        this.friendsList = friendsList;
    }

    public void setSharedPlaylist(SharedPlaylist sharedPlaylist) {
        this.sharedPlaylist = sharedPlaylist;
    }
    public SharedPlaylist getSharedPlaylist() {
        return sharedPlaylist;
    }

    public void updateFriendsLastSong()
    {
        for(Friend f : friendsList)
        {
            try {
                f.updateLastArtwork();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
