package JPotifyGUI;

import JPotifyGUI.BottomPanel.BottomPanel;
import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyGUI.RightPanel.RightPanel;
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

    /**
     * @param fileManager gets the primary object from Logic part
     *                    required fields from logic are gathered inside fileManager
     */
    public GUI(FileManager fileManager, NetworkManager networkManager) {
        fileManager.loadData();
        networkManager.loadData();
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
        RightPanel rightPanel = new RightPanel(networkManager);

        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);

        MyTimeRunnable myTimeRunnable = new MyTimeRunnable(centerPanel, networkManager, rightPanel);
        Thread timerThread = new Thread(myTimeRunnable);
        timerThread.start();

        frame.setVisible(true);
        // intellij's optimization below
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            fileManager.saveData();
            networkManager.saveData();
        }));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //TODO: Timer thread
    private class MyTimeRunnable implements Runnable {
        private int periodTime = 500; // 500 ms
        private int NetworkPeriodTime = 5000; // 5s
        private CenterPanel centerPanel;
        private NetworkManager networkManager;
        private RightPanel rightPanel;

        public MyTimeRunnable(CenterPanel centerPanel, NetworkManager networkManager, RightPanel rightPanel) {
            this.centerPanel = centerPanel;
            this.networkManager = networkManager;
            this.rightPanel = rightPanel;
        }

        @Override
        public void run() {
            int n = NetworkPeriodTime / periodTime;
            int cnt = 0;
            int sec = 0;
            int min, hour;
            int val;
            while (true) {
                cnt++;
                try {
                    Thread.sleep(periodTime);

                    this.centerPanel.getLeftPanel().setImageData(player.getSong().getImageData());
                    this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().paint();
                    this.centerPanel.getBottomPanel().setMusicSlider((int) Player.getElapsedTimeInPercent(), 0, 100);
                    val = (int) Player.getElapsedTimeInSecond();
                    sec = Math.floorMod(val, 60);
                    min = Math.floorMod(val / 60, 60);
                    hour = val / 60;
                    if (val > 3600)
                        this.centerPanel.getBottomPanel().setElapse("" + hour + ":" + min + ":" + sec);
                    else
                        this.centerPanel.getBottomPanel().setElapse("" + min + ":" + sec);

                    val = (int) Player.getTotalTimeInSecond();
                    sec = Math.floorMod(val, 60);
                    min = Math.floorMod(val / 60, 60);
                    hour = val / 60;
                    if (val > 3600)
                        this.centerPanel.getBottomPanel().setTotal("" + hour + ":" + min + ":" + sec);
                    else
                        this.centerPanel.getBottomPanel().setTotal("" + min + ":" + sec);
                    this.centerPanel.getBottomPanel().setIconPlayPause(Player.isPlaying());
                    if (cnt >= n) // check network
                    {
                        this.networkManager.updateFriendsLastSong();
                        this.rightPanel.getRightPanelsFriendsPanel().paint();
                        cnt = 0;
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
}