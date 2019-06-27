package JPotifyLogic.Network;

import JPotifyLogic.Entity.Artwork;

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
    private String status = "offline";
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
        try {
            this.socket = new Socket(this.ip, this.port);
            this.status = "online";
        } catch (IOException e) {
//            e.printStackTrace();
            this.status = "offline";
            return;
        }
        // Streams for communication with server
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("give me lastArtwork");
        String status = in.readLine();
        if (status.equals("nothing")) {
            //pass

        } else if (status.equals("ok")) {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            lastArtwork = (Artwork) ois.readObject();
        }

        this.socket.close();
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

}
