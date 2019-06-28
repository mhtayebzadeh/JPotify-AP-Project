package JPotifyLogic;

import JPotifyLogic.Entity.Song;
import JPotifyLogic.Entity.SongMinimumData;
import JPotifyLogic.Network.Friend;
import JPotifyLogic.Network.Server;
import JPotifyLogic.Playlist.Playlist;
import JPotifyLogic.Playlist.PlaylistMinData;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NetworkManager {
    private static SharedPlaylist sharedPlaylist;
    private ArrayList<Friend> friendsList;
    private String defaultSaveDir = "savedData";
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

    private void loadData(String dataDirectory) {
        try {
            FileInputStream fis = new FileInputStream(Paths.get(dataDirectory, "friends.ser").toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println(Paths.get(dataDirectory, "friends.ser").toString());
            this.friendsList = (ArrayList<Friend>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the data from the defaultSaveDir to the program
     */
    public void loadData() {
        loadData(this.defaultSaveDir);
    }

    /**
     * @param dataDirectory saves the program's current data to the given directory
     */
    private void saveData(String dataDirectory) {
        File theDir = new File(dataDirectory);
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (Exception ignored) {
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(Paths.get(dataDirectory, "friends.ser").toString());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.friendsList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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