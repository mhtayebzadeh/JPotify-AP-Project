package JPotifyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class EntityPanel extends JPanel {
    private int index;

    public EntityPanel(String title, String caption, byte[] imageData, int index) {
        super();
        this.index = index;
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.ORANGE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon bImageIcon = new ImageIcon(ImageIO.read(bis));
            Image bImage = bImageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(bImage));
            imageLabel.setLocation(dim.width/3, dim.height/3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(imageLabel);
        this.add(new JLabel(title));
        this.add(new JLabel(caption));
//        this.setMaximumSize(new Dimension(20, 30));
//        this.setMinimumSize(new Dimension(10, 15));
    }

    public int getIndex() {
        return index;
    }
}
