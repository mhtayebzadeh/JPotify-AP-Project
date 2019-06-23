package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private int port = 3663;
    private static Artwork myLastArtwork;
    public Server()
    {
        super();
    }

    public static void setMyLastArtwork(Artwork myLastArtwork_) {
        myLastArtwork = myLastArtwork_;
    }

    public Artwork getMyLastArtwork() {
        return this.myLastArtwork;
    }

    public void run() {
        while (true)
        {
            System.out.println("Server is running ...");
            try (ServerSocket listener = new ServerSocket(port)) {
                while (true) {
                    //System.out.println("Waiting for a client to connect...");
                    new MyClientHandler(listener.accept() , this.myLastArtwork).start();
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


}
class MyClientHandler extends Thread {
    private Socket socket;
    private Artwork lastArtwork = null;
    public MyClientHandler(Socket socket,Artwork lastArtwork){
        this.socket = socket;
        this.lastArtwork = lastArtwork;
    }
    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Get messages from the client, line by line; return them capitalized
            if (in.readLine().equals("give me lastArtwork"))
            {
                if(this.lastArtwork == null)
                {
                    out.println("nothing");
                }
                else
                {
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