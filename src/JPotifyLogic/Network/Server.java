package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;

import java.io.Serializable;

public class Server extends Thread{
    private int port = 3663;
    private Artwork myLastArtwork;
    public Server()
    {
        super();
    }

    public void setMyLastArtwork(Artwork myLastArtwork) {
        this.myLastArtwork = myLastArtwork;
    }


    @Override
    public void run() {
        while (true)
        {
            // nothing
        }
    }
}
