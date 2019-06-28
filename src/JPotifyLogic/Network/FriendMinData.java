package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FriendMinData implements Serializable {
    private int port = 3663; // :|
    private String name;
    private String hostName;
    private Artwork lastArtwork;
    public FriendMinData(String name,String hostName,Artwork lastArtwork,int port)
    {
        this.name = name;
        this.hostName = hostName;
        this.lastArtwork = lastArtwork;
        this.port = port;
    }
    public FriendMinData(Friend friend)
    {
        this.port = friend.getPort();
        this.hostName = friend.getHostName();
        this.lastArtwork = friend.getLastArtwork();
        this.name = friend.getName();
    }

    public Friend getFriend()
    {
        Friend f = null;
        f = new Friend(this.name,this.hostName);
        f.setLastArtwork(this.lastArtwork);
        f.setPort(this.port);
        return f;
    }
}
