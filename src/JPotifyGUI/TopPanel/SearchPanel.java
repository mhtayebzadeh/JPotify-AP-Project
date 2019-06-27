package JPotifyGUI.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class SearchPanel extends JPanel {
    public SearchPanel()
    {
        super();
        JTextField searchBox = new JTextField("Search");
        searchBox.setToolTipText("Search");

        this.add(searchBox);
        JLabel pic = new JLabel();
        pic.setIcon(new ImageIcon("src/JPotifyGUI/images/search.png"));
        pic.setPreferredSize(new Dimension(30,30));
        this.add(pic);
        this.setPreferredSize(new Dimension(150,30));
        //TODO: search panel not completed.

    }


}
