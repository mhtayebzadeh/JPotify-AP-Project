package JPotifyGUI;

import JPotifyLogic.Library.Songs;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private Songs songs;

    public LeftPanel(Songs songs) {
        super();
        this.songs = songs;
//        this.setSize(new Dimension(100,200));
//        this.setMinimumSize(new Dimension(100,300));
        this.setPreferredSize(new Dimension(100, 200));
        this.setBackground(Color.RED);
        this.setLayout(new GridLayout(4,1));

        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("Songs");
        list.addElement("Albums");
        list.addElement("Artists");
        JList<String> libraryList = new JList<>(list);

        this.add(new LeftPanelsAddPanel(songs));
        this.add(libraryList);
    }
}
