import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;
import JPotifyLogic.NetworkManager;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        NetworkManager networkManager = new NetworkManager();
        GUI gui = new GUI(fileManager,networkManager);
        System.out.println("JPotify Start ...");
    }
}