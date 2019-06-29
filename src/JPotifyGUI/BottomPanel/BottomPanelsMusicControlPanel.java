package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * this class is created as bottom music control panel
 * play, pause, stop, previous, next, repeat and shuffle
 * are the control functions this panel can perform
 */
public class BottomPanelsMusicControlPanel extends Container {
    private JLabel[] controlButtons;
    private JSlider musicSlider;
    private Player player;
    private boolean _mouseAction = false;
    private long sliderVal = 0;
    private JLabel elapse;
    private JLabel total;
    private ImageIcon imageIconPlay;
    private ImageIcon imageIconPause;
    private ImageIcon imageIconloopOn;
    private ImageIcon imageIconloopOff;
    private ImageIcon imageIconShuffleOn;
    private ImageIcon imageIconShuffleOff;

    /**
     * @param player gets the player object to perform actions on it
     *               using the control buttons
     */
    public BottomPanelsMusicControlPanel(Player player) {
        super();
        this.player = player;

        String playIcon = "play.png";
        String pauseIcon = "pause.png";
        String shuffleOnIcon = "shuffle_on.png";
        String shuffleOffIcon = "shuffle_off.png";
        String loopOnIcon = "loop_on.png";
        String loopOffIcon = "loop_off.png";
        int btnSize = GUI.dim.height / 35;
        try {
            imageIconPlay = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + playIcon))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));
            imageIconPause = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + pauseIcon))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));

            imageIconloopOff = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + loopOffIcon))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));
            imageIconloopOn = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + loopOnIcon))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));

            imageIconShuffleOff = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + shuffleOffIcon))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));
            imageIconShuffleOn = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + shuffleOnIcon))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel buttonsPanel = new JPanel();
        this.setBackground(GUI.bottomColorBlack);
        buttonsPanel.setBackground(GUI.bottomColorBlack);
//        buttonsPanel.setLayout(new GridLayout(1, 5));
//        buttonsPanel.setLayout(new BoxLayout(this , BoxLayout.X_AXIS));

        String[] addresses = {"shuffle_off.png", "backward-1.png",
                playIcon, "fast-forward.png", "loop_off.png"};
        MouseListener[] mouseListeners = {new ShuffleMouseListener(), new PreviousMouseListener(),
                new PlayPauseMouseListener(), new NextMouseListener(), new RepeatMouseListener()};
        this.controlButtons = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            try {
                ImageIcon ii = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/multimedia/" + addresses[i])));
                Image image = ii.getImage().getScaledInstance(btnSize, btnSize, Image.SCALE_SMOOTH);
                this.controlButtons[i] = new JLabel();
                this.controlButtons[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                this.controlButtons[i].setIcon(new ImageIcon(image));
                this.controlButtons[i].addMouseListener(mouseListeners[i]);
                this.controlButtons[i].setBackground(GUI.bottomColorBlack);
                buttonsPanel.add(this.controlButtons[i]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        musicSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
//            musicSlider.setPaintTicks(false);
//            musicSlider.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        musicSlider.setBackground(GUI.bottomColorBlack);
        musicSlider.addChangeListener(changeEvent -> {
            //TODO: problem of listener
            if (_mouseAction)
                sliderVal = musicSlider.getValue();
//                        player.gotoPercent(100* (float)musicSlider.getValue()/(float)musicSlider.getMaximum());
            System.out.println((float) 100 * musicSlider.getValue() / (float) musicSlider.getMaximum());
        });
        musicSlider.addMouseListener(new SliderMouseListener());

        musicSlider.setPreferredSize(new Dimension(GUI.dim.width / 3, 40));
        musicSlider.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        elapse = new JLabel("0:00");
        elapse.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 15));
        elapse.setForeground(GUI.captionColorGrey);
        total = new JLabel("5:36");
        total.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 50));
        total.setForeground(GUI.captionColorGrey);
        p.add(elapse, BorderLayout.WEST);
        p.add(musicSlider, BorderLayout.CENTER);
        p.add(total, BorderLayout.EAST);
        p.setBackground(GUI.bottomColorBlack);
//            p.setPreferredSize(new Dimension(200 , 50));

        this.setLayout(new BorderLayout());
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.add(p, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    public void setMusicSliderInitValue(int val, int min, int max) {

        this.musicSlider.setMinimum(min);
        this.musicSlider.setMaximum(max);
        if (!_mouseAction)
            this.musicSlider.setValue(val);
//        _mouseAction = false;
    }

    public void setElapse(String elapseStr) {
        elapse.setText(elapseStr);
    }

    public void setTotal(String totalStr) {
        total.setText(totalStr);
    }

    public void setIconPlayPause(boolean isPlaying) {
        JLabel playPause = controlButtons[2];
        if (isPlaying)
            playPause.setIcon(imageIconPause);
        else
            playPause.setIcon(imageIconPlay);
    }

    /**
     * mouse listener for the slider panel in the bottom
     * mouse pressed and released methods are used here
     */
    public class SliderMouseListener implements MouseListener {
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
    private class ShuffleMouseListener implements MouseListener {
        private boolean shuffleState = false;

        @Override
        public void mouseClicked(MouseEvent e) {
            shuffleState = !shuffleState;
            Player.setShuffle(shuffleState);
            if (shuffleState)
                controlButtons[0].setIcon(imageIconShuffleOn);
            else
                controlButtons[0].setIcon(imageIconShuffleOff);

            // TODO: shuffle not complete
            player.setShuffle();
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
        @Override
        public void mouseClicked(MouseEvent e) {
            if (Player.isPlaying())
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
    private class RepeatMouseListener implements MouseListener {
        private boolean repeatState = false;

        @Override
        public void mouseClicked(MouseEvent e) {
            repeatState = !repeatState;
            Player.setRepeat(repeatState);
            if (repeatState)
                controlButtons[4].setIcon(imageIconloopOn);
            else
                controlButtons[4].setIcon(imageIconloopOff);
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
