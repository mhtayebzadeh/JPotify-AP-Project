package JPotifyGUI;

import JPotifyGUI.BottomPanel.BottomPanel;
import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.FileManager;
import JPotifyLogic.NetworkManager;
import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;

/**
 * the primary Graphical User Interface class which is instantiated in main function
 * each panel or component is implemented in different packages and classes for cleanness
 * as a result of the above mentioned reason, different parts are passes tp eachother
 * so that events in one can alter another using listeners
 */
public class GUI {
    public static final Color captionColorGrey = new Color(180, 180, 180);
    public static final Color bottomColorBlack = new Color(100, 100, 100);
    public static final Color bgColorBlack = new Color(43, 43, 43);
    public static final Color sideColorBlack = new Color(15, 15, 15);
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private Player player; // field from logic which is needed in gui besides fileManager
    private NetworkManager networkManager;
    private Thread timerThread;

    /**
     * @param fileManager gets the primary object from Logic part
     *                    required fields from logic are gathered inside fileManager
     */
    public GUI(FileManager fileManager, NetworkManager networkManager) {
        fileManager.loadData();
        this.player = new Player("jpotify");

        JFrame frame = new JFrame("JPotify");
        frame.setSize(3 * dim.width / 4, 3 * dim.height / 4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/JPotifyGUI/images/jpotify_icon.png").getImage());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //set initial position of frame in center of screen
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setBackground(GUI.bgColorBlack);

        BottomPanel bottomPanel = new BottomPanel(this.player);
        CenterPanel centerPanel = new CenterPanel(this.player, fileManager, bottomPanel, null);
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        LeftPanel leftPanel = new LeftPanel(centerPanel);
        centerPanel.setLeftPanel(leftPanel);
        RightPanel rightPanel = new RightPanel();

        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                fileManager.saveData();
            }
        }));
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    //TODO: Timer thread

    private class MyTimeRunnable implements Runnable {
        private int periodTime = 500; // 500 ms
        private int NetworkPeriodTime = 5000; // 5s
        private Player player;
        private FileManager fileManager;
        private NetworkManager networkManager;
        private BottomPanel bottomPanel;
        private LeftPanel leftPanel;

        public MyTimeRunnable(Player player, FileManager fileManager, NetworkManager networkManager, BottomPanel bottomPanel, LeftPanel leftPanel) {
            this.player = player;
            this.bottomPanel = bottomPanel;
            this.leftPanel = leftPanel;
            this.fileManager = fileManager;
            this.networkManager = networkManager;
        }

        @Override
        public void run() {
            int n = NetworkPeriodTime / periodTime;
            int cnt = 0;
            while (true) {
                cnt++;
                try {
                    Thread.sleep(periodTime);
                    this.bottomPanel.setMusicSlider((int) player.getElapsedTimeInPercent(), 0, 100);

                    if (cnt >= n) { // check network
                        cnt = 0;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}