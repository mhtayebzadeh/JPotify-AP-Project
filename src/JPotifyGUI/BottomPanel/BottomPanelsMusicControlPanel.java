package JPotifyGUI.BottomPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class BottomPanelsMusicControlPanel extends JPanel {
    private JButton[] controlButtons;

    public BottomPanelsMusicControlPanel() {
        super();
        this.setLayout(new GridLayout(1, 5));
        String[] addresses = {"icons8-shuffle-100.png", "icons8-skip-to-start-100.png",
                "icons8-circled-play-100.png", "icons8-end-100.png", "icons8-repeat-100.png"};
        MouseListener[] mouseListeners = {new ShuffleMouseListener(), new PreviousMouseListener(),
                new PlayPauseMouseListener(), new NextMouseListener(), new RepeatMouseListener()};
        this.controlButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            try {
                ImageIcon ii = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/music control/" + addresses[i])));
                Image image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                this.controlButtons[i] = new JButton();
                this.controlButtons[i].setIcon(new ImageIcon(image));
                this.controlButtons[i].addMouseListener(mouseListeners[i]);
                this.add(this.controlButtons[i]);
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ShuffleMouseListener implements MouseListener {
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

    public class PreviousMouseListener implements MouseListener {
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

    public class PlayPauseMouseListener implements MouseListener {
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

    public class NextMouseListener implements MouseListener {
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

    public class RepeatMouseListener implements MouseListener {
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
