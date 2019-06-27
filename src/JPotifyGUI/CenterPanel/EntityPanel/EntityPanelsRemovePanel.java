package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class EntityPanelsRemovePanel extends JPanel {
    public EntityPanelsRemovePanel(Entity entity) {
        super();
        this.setBackground(GUI.bgColorBlack);

        if (!(entity instanceof Album || entity instanceof Artist)) {
            JLabel removeLabel = new JLabel();
            removeLabel.addMouseListener(new RemoveMouseListener());
            try {
                ImageIcon removeIcon = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/add and remove/remove_icon.png")));
                Image removeImage = removeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                removeLabel.setIcon(new ImageIcon(removeImage));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            this.add(removeLabel);
        }
    }

    private class RemoveMouseListener implements MouseListener {
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
}
