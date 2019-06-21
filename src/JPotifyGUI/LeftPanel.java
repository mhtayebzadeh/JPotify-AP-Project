package JPotifyGUI;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {

    public LeftPanel() {
        super();
//        this.setSize(new Dimension(100,200));
//        this.setMinimumSize(new Dimension(100,300));
        this.setPreferredSize(new Dimension(200, 200));
        this.setBackground(Color.RED);
        this.setLayout(new GridLayout(4,1));


        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("Songs");
        list.addElement("Albums");
        list.addElement("Artists");
        JList<String> libraryList = new JList<>(list);

        this.add(new LeftPanelsAddPanel());
        this.add(libraryList);
    }
}
