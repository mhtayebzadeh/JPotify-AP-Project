package JPotifyGUI;

import javax.swing.*;
import java.awt.*;

public class SongPanel extends JPanel {
    public SongPanel(String title, String albumName) //TODO: get Image
    {
        super();
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.ORANGE);
        this.add(new JLabel(title));
        this.add(new JLabel(albumName));
//        this.setMaximumSize(new Dimension(20, 30));
//        this.setMinimumSize(new Dimension(10, 15));
    }
}
