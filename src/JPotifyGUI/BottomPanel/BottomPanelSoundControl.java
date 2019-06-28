package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.SystemControl.Audio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BottomPanelSoundControl extends JPanel {
    JSlider slider;
    public BottomPanelSoundControl()
    {
        super();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, -1));
        this.setBackground(GUI.sideColorBlack);
        slider = new JSlider(JSlider.HORIZONTAL,0,100,50);
        Audio.setMasterOutputVolume(0.5f);
        slider.addMouseListener(new SoundControlListener());
        BorderLayout b = new BorderLayout();
        b.setVgap(100);

//        this.setLayout(b);
        this.add(slider);

    }
    public class SoundControlListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            float val = ((float)slider.getValue()/(float)slider.getMaximum());
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
