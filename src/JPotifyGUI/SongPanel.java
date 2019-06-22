package JPotifyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SongPanel extends JPanel {
    public SongPanel(String title, String albumName, byte[] imageData) //TODO: get Image
    {
        super();
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.ORANGE);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        JButton imageButton = new JButton();
        try {
            ImageIcon bImageIcon = new ImageIcon(ImageIO.read(bis));
            Image bImage = bImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageButton.setIcon(new ImageIcon(bImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(imageButton);
        this.add(new JLabel(title));
        this.add(new JLabel(albumName));
//        this.setMaximumSize(new Dimension(20, 30));
//        this.setMinimumSize(new Dimension(10, 15));
    }

}
