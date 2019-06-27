package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Friend {
    private int port = 3663; // :|
    private String name;
    private InetAddress ip;
    private String hostName;
    private Artwork lastArtwork;
    private Socket socket;

    public Friend(String name) throws IOException {
        this(name, "localhost");
    }

    public Friend(String name, String host) throws UnknownHostException {
        this(name, host, InetAddress.getByName(host));
    }


    public Friend(String name, String host, InetAddress ip) {
        this.name = name;
        this.hostName = host;
        this.ip = ip;
    }


    /**
     * @return last artwork of your friend ;)
     */
    public Artwork getLastArtwork() {
        return lastArtwork;
    }

    public void updateLastArtwork() throws IOException, ClassNotFoundException {
        //TODO:
        this.socket = new Socket(this.ip, this.port);
        // Streams for communication with server
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) { }
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("give me lastArtwork");
        String status = in.readLine();
        if (status.equals("nothing")) {
            //pass
<<<<<<< HEAD
        } else if (status.equals("ok")) {
=======
        }

        else if(status.equals("ok"))
        {
>>>>>>> dbf8c929386d1e33598b4a45b987ef63d90443f9
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            lastArtwork = (Artwork) ois.readObject();
        }

        this.socket.close();
    }

}
