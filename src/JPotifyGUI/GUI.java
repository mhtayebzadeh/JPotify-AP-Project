package JPotifyGUI;

import JPotifyGUI.BottomPanel.BottomPanel;
import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.FileManager;
import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static final Color captionColorGrey = new Color(180, 180, 180);
    public static final Color bottomColorBlack = new Color(100, 100, 100);
    public static final Color bgColorBlack = new Color(43, 43, 43);
    public static final Color sideColorBlack = new Color(15, 15, 15);

    private BottomPanel bottomPanel;
    private CenterPanel centerPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private JFrame frame;

    // fields from logic
    private FileManager fileManager;
    private Player player;

    public GUI(FileManager fileManager) {
        this.fileManager = fileManager;
        this.fileManager.loadData();

        this.frame = new JFrame("JPotify");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setSize(3*dim.width/4, 3*dim.height/4);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(new ImageIcon("src/JPotifyGUI/images/jpotify_icon.png").getImage());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //set initial position of frame in center of screen
        this.frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        this.frame.setBackground(Color.BLACK);

        this.bottomPanel = new BottomPanel(this.player);
        this.centerPanel = new CenterPanel(this.player, this.fileManager, this.bottomPanel, this.leftPanel);
        JScrollPane jScrollPane = new JScrollPane(this.centerPanel);
        this.leftPanel = new LeftPanel(this.centerPanel);
        this.centerPanel.setLeftPanel(this.leftPanel);
        this.rightPanel = new RightPanel();

        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.bottomPanel, BorderLayout.SOUTH);
        this.frame.add(jScrollPane, BorderLayout.CENTER);
        this.frame.add(this.leftPanel, BorderLayout.WEST);
        this.frame.add(this.rightPanel, BorderLayout.EAST);

        this.frame.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread(new ExitRunnable(fileManager)));
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.centerPanel.setPlayer(player);
    }

    private class ExitRunnable implements Runnable {
        private FileManager fileManager;

        public ExitRunnable(FileManager fileManager) {
            this.fileManager = fileManager;
        }

        @Override
        public void run() {
            this.fileManager.saveData();
        }
    }

    //    public void setSongsPanel(ArrayList<SongPanel> songs) {
//        centerPanel.setSongs(songs);
//    }
}