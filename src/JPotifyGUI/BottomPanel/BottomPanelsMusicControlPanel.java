package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class BottomPanelsMusicControlPanel extends Container {
    private JLabel[] controlButtons;
    JPanel buttonsPanel ;
    JSlider musicSlider;
    Player player;
    boolean _mouseAction = false;
    long sliderVal = 0;
    private JLabel elapse;
    private JLabel total;
    String playIcon = "play.png";
    String pauseIcon = "pause.png";
    private ImageIcon imageIconPlay ;
    private ImageIcon imageIconPause;

    public BottomPanelsMusicControlPanel(Player player) {
        super();
        this.player = player;

        try {
            imageIconPlay = new ImageIcon(new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/multimedia/" + playIcon))).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            imageIconPause = new ImageIcon(new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/multimedia/" + pauseIcon))).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonsPanel = new JPanel();
        this.setBackground(GUI.bottomColorBlack);
        buttonsPanel.setBackground(GUI.bottomColorBlack);
//        buttonsPanel.setLayout(new GridLayout(1, 5));
//        buttonsPanel.setLayout(new BoxLayout(this , BoxLayout.X_AXIS));

        String[] addresses = {"shuffle.png", "backward-1.png",
                playIcon, "fast-forward.png", "random(1).png"};
        MouseListener[] mouseListeners = {new ShuffleMouseListener(), new PreviousMouseListener(),
                new PlayPauseMouseListener(), new NextMouseListener(), new RepeatMouseListener()};
        this.controlButtons = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            try {
                ImageIcon ii = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/multimedia/" + addresses[i])));
                Image image = ii.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                this.controlButtons[i] = new JLabel();
                this.controlButtons[i].setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
                this.controlButtons[i].setIcon(new ImageIcon(image));
                this.controlButtons[i].addMouseListener(mouseListeners[i]);
                this.controlButtons[i].setBackground(GUI.bottomColorBlack);
                buttonsPanel.add(this.controlButtons[i]);

            } catch (
                    IOException e) {
                e.printStackTrace();
            }

        }

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        musicSlider = new JSlider(JSlider.HORIZONTAL, 0,100,0);
//            musicSlider.setPaintTicks(false);
//            musicSlider.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        musicSlider.setBackground(GUI.bottomColorBlack);
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
//T.ODO: problem of listener
                if(_mouseAction)
                    sliderVal = musicSlider.getValue();
//                        player.gotoPercent(100* (float)musicSlider.getValue()/(float)musicSlider.getMaximum());
                System.out.println((float)100* musicSlider.getValue()/(float)musicSlider.getMaximum());
            }
        });
        musicSlider.addMouseListener(new SliderMouseListener());
        musicSlider.setPreferredSize(new Dimension(GUI.dim.width/3,40));
        musicSlider.setBorder(BorderFactory.createEmptyBorder(5,0,10,0));
        elapse = new JLabel("0:00");
        elapse.setBorder(BorderFactory.createEmptyBorder(0,50,0,15));
        elapse.setForeground(GUI.captionColorGrey);
        total = new JLabel("5:36");
        total.setBorder(BorderFactory.createEmptyBorder(0,15,0,50));
        total.setForeground(GUI.captionColorGrey);
        p.add(elapse,BorderLayout.WEST);
        p.add(musicSlider,BorderLayout.CENTER);
        p.add(total,BorderLayout.EAST);
        p.setBackground(GUI.bottomColorBlack);
//            p.setPreferredSize(new Dimension(200 , 50));

        this.setLayout(new BorderLayout());
        this.add(buttonsPanel,BorderLayout.CENTER);
        this.add(p,BorderLayout.SOUTH);
        this.setVisible(true);

    }

    public void setMusicSliderInitValue(int val,int min , int max)
    {

        this.musicSlider.setMinimum(min);
        this.musicSlider.setMaximum(max);
        if (!_mouseAction)
            this.musicSlider.setValue(val);
//        _mouseAction = false;
    }

    public void setMusicSliderValue(float percent) {
        this.musicSlider.setValue((int) percent);
    }

    public void setElapse(String elapseStr)
    {
        elapse.setText(elapseStr);
    }
    public void setTotal(String totalStr)
    {
        total.setText(totalStr);
    }
    public void setIconPlayPause(boolean isPlaying)
    {
        JLabel playPause = controlButtons[2];
        if(isPlaying)
            playPause.setIcon(imageIconPause);
        else
            playPause.setIcon(imageIconPlay);
    }
    public class SliderMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            _mouseAction = true;

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            _mouseAction = false;
            player.gotoPercent(100 * (float) sliderVal / (float) musicSlider.getMaximum());
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    /**
     * this mouse listener is added as shuffle button's listener
     */
    //TODO: implement
    private class ShuffleMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            player.setShuffle();
            // TODO:

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    /**
     * this mouse listener is added as previous button's listener
     */
    //TODO: implement
    private class PreviousMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            player.previousSong();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    /**
     * this mouse listener is added as play/pause button's listener
     */
    private class PlayPauseMouseListener implements MouseListener {
//        private Player player;
//        private JButton playPauseButton;


        @Override
        public void mouseClicked(MouseEvent e) {

            if(Player.isPlaying())
                player.pause();

            else
                player.resume_();
            setIconPlayPause(Player.isPlaying());

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    /**
     * this mouse listener is added as next button's listener
     */
    //TODO: implement
    private class NextMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            player.nextSong();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    /**
     * this mouse listener is added as repeat button's listener
     */
    //TODO: implement
    private class RepeatMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO:
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
