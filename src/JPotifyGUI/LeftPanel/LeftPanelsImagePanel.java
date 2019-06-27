package JPotifyGUI.LeftPanel;

import JPotifyGUI.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * this panel is for the image of the current playing song
 * displays a default music icon at the start
 */
public class LeftPanelsImagePanel extends JPanel {
    public LeftPanelsImagePanel() {
        super();
        this.setPreferredSize(new Dimension(
                GUI.dim.width / 8, GUI.dim.width / 8));
        this.setBackground(GUI.sideColorBlack);
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/music_icon.png")));
            Image image = ii.getImage().getScaledInstance(
                    GUI.dim.width / 8, GUI.dim.width / 8, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(imageLabel);
    }
}
