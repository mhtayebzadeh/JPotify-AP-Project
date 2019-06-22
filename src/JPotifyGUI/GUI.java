package JPotifyGUI;

//import com.sun.media.sound.RIFFInvalidDataException;

import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.LogicData;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static final Color bg_color_black = new Color(43, 43, 43);
    private BottomPanel bottomPanel;
    private CenterPanel centerPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private JFrame frame;

    // fields from logic
    private LogicData logicData;

    public GUI() {
        this.logicData = new LogicData();

        this.frame = new JFrame("JPotify");
        this.frame.setSize(800, 500);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(new ImageIcon("src/JPotifyGUI/images/jpotify_icon.png").getImage());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //set initial position of frame in center of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        this.frame.setBackground(Color.BLACK);

        this.bottomPanel = new BottomPanel();
        this.centerPanel = new CenterPanel();
        this.leftPanel = new LeftPanel(this.logicData, this.centerPanel);
        this.rightPanel = new RightPanel();

        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.bottomPanel, BorderLayout.SOUTH);
        this.frame.add(this.centerPanel, BorderLayout.CENTER);
        this.frame.add(this.leftPanel, BorderLayout.WEST);
        this.frame.add(this.rightPanel, BorderLayout.EAST);

        this.frame.setVisible(true);
    }

//    public void setSongsPanel(ArrayList<SongPanel> songs) {
//        centerPanel.setSongs(songs);
//    }
}
