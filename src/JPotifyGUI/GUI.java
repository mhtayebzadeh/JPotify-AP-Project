package JPotifyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GUI{
    public GUI()
    {
        JFrame frame = new JFrame("JPotify");
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/JPotifyGUI/images/jpotify_icon.png").getImage());
        
        //set initial position of frame in center of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        BottomPanel bottomPanel = new BottomPanel();
        CenterPanel centerPanel = new CenterPanel();

        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel,BorderLayout.SOUTH);
        frame.add(centerPanel,BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
