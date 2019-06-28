package JPotifyGUI;

import JPotifyGUI.BottomPanel.BottomPanel;
import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.FileManager;
import JPotifyLogic.NetworkManager;
import JPotifyLogic.Player;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static final Color captionColorGrey = new Color(180, 180, 180);
    public static final Color bottomColorBlack = new Color(100, 100, 100);
    public static final Color bgColorBlack = new Color(43, 43, 43);
    public static final Color sideColorBlack = new Color(15, 15, 15);
    public static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private BottomPanel bottomPanel;
    private CenterPanel centerPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private JFrame frame;

    // fields from logic
    private FileManager fileManager;
    private Player player;
    private NetworkManager networkManager;
    private Thread timerThread;

    public GUI(FileManager fileManager,NetworkManager networkManager) {
        this.fileManager = fileManager;
        this.networkManager = networkManager;
        this.fileManager.loadData();
        this.player = new Player("jpotify");
        this.frame = new JFrame("JPotify");
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
        this.frame.setBackground(GUI.bgColorBlack);

        this.bottomPanel = new BottomPanel(this.player);
        this.centerPanel = new CenterPanel(this.player, this.fileManager, this.bottomPanel, this.leftPanel);
        JScrollPane scrollPane = new JScrollPane(this.centerPanel);
        this.leftPanel = new LeftPanel(this.centerPanel);
        this.centerPanel.setLeftPanel(this.leftPanel);
        this.rightPanel = new RightPanel();

        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.bottomPanel, BorderLayout.SOUTH);
        this.frame.add(scrollPane, BorderLayout.CENTER);
        this.frame.add(this.leftPanel, BorderLayout.WEST);
        this.frame.add(this.rightPanel, BorderLayout.EAST);

        MyTimeRunnable myTimeRunnable = new MyTimeRunnable(player,fileManager,networkManager,bottomPanel,leftPanel);
        this.timerThread = new Thread(myTimeRunnable);
        this.timerThread.start();

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

    //TODO: Timer thread

    private class MyTimeRunnable implements Runnable {
        private int periodTime = 500 ; // 500 ms
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
            int sec = 0;
            int min, hour;
            int val;
            while (true)
            {
                cnt++;
                try{
                    Thread.sleep(periodTime);
                    this.bottomPanel.setMusicSlider((int)player.getElapsedTimeInPercent(),0,100);
                    val = (int)Player.getElapsedTimeInSecond();
                    sec = Math.floorMod(val,60);
                    min = Math.floorMod(val/60,60);
                    hour = val / 60;
                    if(val > 3600)
                        this.bottomPanel.setElapse(""+hour+":"+min+":"+sec);
                    else
                        this.bottomPanel.setElapse(""+min+":"+sec);

                    val = (int)Player.getTotalTimeInSecond();
                    sec = Math.floorMod(val,60);
                    min = Math.floorMod(val/60,60);
                    hour = val / 60;
                    if(val > 3600)
                        this.bottomPanel.setTotal(""+hour+":"+min+":"+sec);
                    else
                        this.bottomPanel.setTotal(""+min+":"+sec);

                    bottomPanel.setIconPlayPause(Player.isPlaying());
                    if(cnt >= n) // check network
                    {
                        cnt = 0;
                    }
                }catch (Exception e) {}
            }
        }
    }
}