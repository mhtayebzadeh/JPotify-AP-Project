package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class EntityPanelsHeartPanel extends JPanel {
    public EntityPanelsHeartPanel(FileManager fileManager, Entity entity) {
        super();
        this.setBackground(GUI.bgColorBlack);
        if (entity instanceof Song) {
            Song song = (Song) entity;
            JLabel heartLabel = new JLabel();
            heartLabel.addMouseListener(new HeartMouseListener(fileManager, entity));
            try {
                ImageIcon hw = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_w.png")));
                ImageIcon hr = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_r.png")));
                Image image;
                if (song.isFavorite())
                    image = hr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                else {
                    image = hw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    //this.
                }
                heartLabel.setIcon(new ImageIcon(image));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            this.add(heartLabel);
        }
    }

    private class HeartMouseListener implements MouseListener {
        private Entity entity;
        private FileManager fileManager;

        public HeartMouseListener(FileManager fileManager, Entity entity) {
            this.entity = entity;
            this.fileManager = fileManager;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel heartLabel = (JLabel) e.getSource();
            Song song = (Song) this.entity;

            try {
                ImageIcon hw = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_w.png")));
                ImageIcon hr = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_r.png")));
                Image image;
                if (song.isFavorite()) {
                    image = hr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    song.setFavorite(false);
                }
                else {
                    image = hw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    song.setFavorite(true);
                }
                heartLabel.setIcon(new ImageIcon(image));
            } catch (IOException err) {
                err.printStackTrace();
            }
            this.fileManager.update();
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
