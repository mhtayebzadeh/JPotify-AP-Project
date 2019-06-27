package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static Artwork myLastArtwork;
    private int port = 3663;
    private static SharedPlaylist sharedPlaylist;

    public Server()
    {
        super();
    }

    public Artwork getMyLastArtwork() {
        return myLastArtwork;
    }

    public static void setMyLastArtwork(Artwork myLastArtwork) {
        myLastArtwork = myLastArtwork;
    }

    public void run() {
        ServerSocket listener;
        while (true) {
            System.out.println("Server is running ...");

            try {
                listener = new ServerSocket(port);
                while (true) {
                    //System.out.println("Waiting for a client to connect...");
                    Socket socket = listener.accept();
                    sharedPlaylist = FileManager.getSharedPlaylist();
                    if(sharedPlaylist != null) {

                        if (sharedPlaylist.getSongs().size() > 0) {
                            Song ss = sharedPlaylist.getSongs().get(0);
                            for (Song s : sharedPlaylist.getSongs()) {
                                if (s.getTimeStampLastPlayed() > ss.getTimeStampLastPlayed())
                                    ss = s;
                            }
                            myLastArtwork = ss.getArtwork();

                        }
                    }
                    new MyClientHandler(socket, myLastArtwork).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // nothing
        }
    }

    /**
     * this method not working because of thread
     * @param sharedPlaylist_
     */
    @Deprecated // :)
    public static void setSharedPlaylist(SharedPlaylist sharedPlaylist_) {
        sharedPlaylist = sharedPlaylist_;
        System.out.println("share playlist ok");
    }

}

class MyClientHandler extends Thread {
    private Socket socket;
    private Artwork lastArtwork = null;

    public MyClientHandler(Socket socket, Artwork lastArtwork) {
        this.socket = socket;
        this.lastArtwork = lastArtwork;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Get messages from the client, line by line; return them capitalized
//            System.out.println("wait for give me lastArtwork");
            String txt = in.readLine();
            if (txt.equals("give me lastArtwork"))
            {
                if(this.lastArtwork == null)
                {
                    out.println("nothing");
                } else {
                    out.println("ok");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) { }

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(this.lastArtwork);
                    oos.close();
                }
            }

        } catch (IOException e) {
            System.out.println("Error handling client");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Connection Exception close");
            }

        }
    }
}