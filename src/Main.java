import JPotifyGUI.GUI;
import JPotifyLogic.Player;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();

        System.out.println("JPotify Start ...");
        gui.setPlayer(new Player("jpotify"));
//        gui.setSongsPanel(s);

    }
}
