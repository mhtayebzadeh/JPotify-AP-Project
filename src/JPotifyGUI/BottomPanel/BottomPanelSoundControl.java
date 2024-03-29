package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.SystemControl.Audio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class BottomPanelSoundControl extends JPanel {
    private JSlider slider;
    private ImageIcon imageIconSoundOn;
    private ImageIcon imageIconSoundOff;
    private JLabel soundIcon;

    public BottomPanelSoundControl() {
        super();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width / 8, 40));

        this.setBackground(GUI.bottomColorBlack);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 70);
        Audio.setMasterOutputVolume(0.7f);
        slider.addMouseListener(new SoundControlListener());
        slider.setPreferredSize(new Dimension(dim.width / 10, 40));
        slider.setBorder(BorderFactory.createEmptyBorder(5, dim.width /120, 5, dim.width /120));
        slider.setBackground(GUI.bottomColorBlack);
        try {
            int btnSize = GUI.dim.width / 60;
            imageIconSoundOn = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + "speaker_on.png"))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));
            imageIconSoundOff = new ImageIcon(new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/multimedia/" + "speaker_off.png"))).getImage().getScaledInstance(
                    btnSize, btnSize, Image.SCALE_SMOOTH));
        } catch (Exception ignored) {
        }
        soundIcon = new JLabel();
        soundIcon.setIcon(imageIconSoundOn);
        soundIcon.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, dim.width /120));
        soundIcon.addMouseListener(new soundIconActionListener());
        this.setLayout(new BorderLayout());
        this.add(slider, BorderLayout.WEST);
        this.add(soundIcon, BorderLayout.EAST);
    }

    public class soundIconActionListener implements MouseListener {
        private boolean mute = false;
        private int lastVolume = 0;

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            mute = !mute;
            if (mute) {
                lastVolume = slider.getValue();
                Audio.setMasterOutputVolume(0.02f);
                soundIcon.setIcon(imageIconSoundOff);
            } else {
                Audio.setMasterOutputVolume((float) lastVolume / 100f);
                soundIcon.setIcon(imageIconSoundOn);
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    public class SoundControlListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            float val = ((float) slider.getValue() / (float) slider.getMaximum());
//            val = val/2f;
            Audio.setMasterOutputVolume(val);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }
    }
}
