package JPotifyGUI;

import com.sun.media.sound.RIFFInvalidDataException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GUI{
    private BottomPanel bottomPanel;
    private CenterPanel centerPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private JFrame frame;
    public static final Color bg_color_black = new Color(43,43,43);
    public GUI()
    {
        frame = new JFrame("JPotify");
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("./src/JPotifyGUI/images/jpotify_icon.png").getImage());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //set initial position of frame in center of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setBackground(Color.BLACK);

        bottomPanel = new BottomPanel();
        centerPanel = new CenterPanel();
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();

        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel,BorderLayout.SOUTH);
        frame.add(centerPanel,BorderLayout.CENTER);
        frame.add(leftPanel , BorderLayout.WEST);
        frame.add(rightPanel,BorderLayout.EAST);
        frame.setVisible(true);
    }

    public void setSongsPanel(ArrayList<SongPanel> songs)
    {
        centerPanel.setSongs(songs);
    }

}
