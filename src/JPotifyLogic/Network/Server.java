package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.SharedPlaylist;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static Artwork myLastArtwork;
    private int port = 3663;
    private SharedPlaylist sharedPlaylist;

    public Server() {
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
                    if (this.sharedPlaylist != null) {
                        if (this.sharedPlaylist.getSongs().size() > 0) {
                            Song ss = this.sharedPlaylist.getSongs().get(0);
                            for (Song s : this.sharedPlaylist.getSongs()) {
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

    public void setSharedPlaylist(SharedPlaylist sharedPlaylist) {
        this.sharedPlaylist = sharedPlaylist;
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
            if (in.readLine().equals("give me lastArtwork")) {
                if (this.lastArtwork == null) {
                    out.println("nothing");
                } else {
                    out.println("ok");
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