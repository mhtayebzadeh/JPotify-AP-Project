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
public class BottomPanelsMusicControlPanel extends JPanel {
    private JSlider musicSlider;
    private Player player;
    private boolean _mouseAction = false;
    private long sliderVal = 0;

    /**
     * @param player gets an object from Player class to
     * use music controls on it
     */
    public BottomPanelsMusicControlPanel(Player player) {
        super();
        this.player = player;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 5));
        String[] addresses = {"icons8-shuffle-100.png", "icons8-skip-to-start-100.png",
                "icons8-circled-play-100.png", "icons8-end-100.png", "icons8-repeat-100.png"};
        JButton[] controlButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            try {
                ImageIcon ii = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/music control/" + addresses[i])));
                Image image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                controlButtons[i] = new JButton();
                controlButtons[i].setIcon(new ImageIcon(image));
                controlButtons[i].setBackground(GUI.bottomColorBlack);
                this.add(controlButtons[i]);
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        MouseListener[] mouseListeners = {new ShuffleMouseListener(), new PreviousMouseListener(),
                new PlayPauseMouseListener(player, controlButtons[2]), new NextMouseListener(), new RepeatMouseListener()};
        for (int i = 0; i < 5; i++)
            controlButtons[i].addMouseListener(mouseListeners[i]);

        JPanel p = new JPanel();
        musicSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        musicSlider.setPaintTicks(false);
        musicSlider.addChangeListener(changeEvent -> {
            //TODO: problem of listener
            if (_mouseAction)
                sliderVal = musicSlider.getValue();
//                        player.gotoPercent(100* (float)musicSlider.getValue()/(float)musicSlider.getMaximum());
            System.out.println((float) 100 * musicSlider.getValue() / (float) musicSlider.getMaximum());
        });
        musicSlider.addMouseListener(new SliderMouseListener());
        p.add(musicSlider);
        this.setLayout(new BorderLayout());
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.add(p, BorderLayout.SOUTH);
    }

    public void setMusicSliderInitValue(int val, int min, int max) {
        this.musicSlider.setMinimum(min);
        this.musicSlider.setMaximum(max);
        if (!_mouseAction)
            this.musicSlider.setValue(val);
//        _mouseAction = false;
    }

    public void setMusicSliderValue(float percent) {
        this.musicSlider.setValue((int) percent);
    }

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
    //TODO: implement
    private class ShuffleMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
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
        private Player player;
        private JButton playPauseButton;

        public PlayPauseMouseListener(Player player, JButton playPauseButton) {
            this.player = player;
            this.playPauseButton = playPauseButton;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                Image image;
                if (player.getSong() == null || player.getSong().isPaused()) {
                    ImageIcon playIcon = new ImageIcon(ImageIO.read(new File(
                            "src/JPotifyGUI/images/music control/icons8-circled-play-100.png")));
                    image = playIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    if (player.getSong() != null)
                        player.getSong().setPaused(false);
                } else {
                    ImageIcon pauseIcon = new ImageIcon(ImageIO.read(new File(
                            "src/JPotifyGUI/images/music control/icons8-pause-button-100.png")));
                    image = pauseIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    player.getSong().setPaused(true);
                }
                this.playPauseButton.setIcon(new ImageIcon(image));
            } catch (IOException err) {
                err.printStackTrace();
            }
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
            // TODO
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
