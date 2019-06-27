import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;
import JPotifyLogic.NetworkManager;
import JPotifyLogic.Player;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        GUI gui = new GUI(fileManager);
        System.out.println("JPotify Start ...");
//        gui.setPlayer(new Player("jpotify"));
//        gui.setSongsPanel(s);

    }
}
