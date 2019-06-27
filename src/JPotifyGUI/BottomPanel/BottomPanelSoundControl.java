package JPotifyGUI.BottomPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.SystemControl.Audio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BottomPanelSoundControl extends JPanel {
    public BottomPanelSoundControl()
    {
        super();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, -1));
        this.setBackground(GUI.sideColorBlack);
        JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                float val = ((float)slider.getValue()/(float)slider.getMaximum());
                val = val/2f;
                Audio.setMasterOutputVolume(val);
            }
        });
        this.setLayout(new CardLayout());
        this.add(slider);

    }

}
