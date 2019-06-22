import JPotifyGUI.GUI;
import JPotifyGUI.SongPanel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();

        System.out.println("JPotify Start ...");
        ArrayList<SongPanel> s = new ArrayList<>();
        s.add(new SongPanel("a", "B"));
        s.add(new SongPanel("baran", "Bahaar"));
        s.add(new SongPanel("kavir", "Sokoot"));
        s.add(new SongPanel("Sama", "Bade Toei"));
        s.add(new SongPanel("trewq", "kjhgfd"));
//        gui.setSongsPanel(s);

    }
}
