import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        GUI gui = new GUI(fileManager);
        System.out.println("JPotify Start ...");
    }
}