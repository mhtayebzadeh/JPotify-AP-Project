package JPotifyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LeftPanel extends JPanel {

    public LeftPanel()
    {
        super();
//        this.setSize(new Dimension(100,200));
//        this.setMinimumSize(new Dimension(100,300));
        this.setPreferredSize(new Dimension(100, 200));
        this.setBackground(Color.RED);
        this.setLayout(new GridLayout(3,1));

        JButton addSongButton = new JButton();
        try {
            addSongButton.setIcon(new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/add_icon.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addSongButton.setText("");
        DefaultListModel<JButton> list = new DefaultListModel<>();
        list.addElement(addSongButton);
        JList<JButton> libraryList = new JList<>(list);

        this.add(libraryList);
    }
}
