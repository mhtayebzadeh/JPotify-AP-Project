package JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class CenterPanel extends JPanel {
    ArrayList<SongPanel> songs = new ArrayList<>();
    public CenterPanel()
    {
        super();
        this.setLayout(new GridLayout(0,3));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public void setSongs(ArrayList<SongPanel> songs) {
        this.songs = songs;
        this.removeAll();
        for(SongPanel s : songs)
        {
            s.setMaximumSize(new Dimension(20,30));
            this.add(s);
        }
        this.validate();
    }
}
