package JPotifyGUI.CenterPanel.EntityPanel.SubPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * this class is created for liking songs and adding them to the favorites playlists
 * the little heart panel on the bottom right corner does this
 */
public class EntityPanelsHeartPanel extends JPanel {
    /**
     * @param centerPanel gets the outer larger center panel to
     *                    both use and set its data when liking songs
     * @param entity      the entity which this entity panel is
     *                    representing it
     */
    public EntityPanelsHeartPanel(CenterPanel centerPanel, Entity entity) {
        super();
        this.setBackground(GUI.bgColorBlack);
        if (entity instanceof Song) {
            Song song = (Song) entity;
            JLabel heartLabel = new JLabel();
            heartLabel.addMouseListener(new HeartMouseListener(centerPanel, entity));
            try {
                Image image;
                if (song.isFavorite()) {
                    ImageIcon hr = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_r.png")));
                    image = hr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                } else {
                    ImageIcon hw = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_w.png")));
                    image = hw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                }
                heartLabel.setIcon(new ImageIcon(image));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.add(heartLabel);
        }
    }

    /**
     * the mouse listener which turns the heart panel red when liking songs
     * and turning it to an empty white heart when disliking songs
     * only mouseClicked method is overridden
     */
    private class HeartMouseListener implements MouseListener {
        private Entity entity;
        private CenterPanel centerPanel;

        public HeartMouseListener(CenterPanel centerPanel, Entity entity) {
            this.entity = entity;
            this.centerPanel = centerPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel heartLabel = (JLabel) e.getSource();
            Song song = (Song) this.entity;

            try {
                Image image;
                if (song.isFavorite()) {
                    ImageIcon hw = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_w.png")));
                    image = hw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    song.setFavorite(false);
                } else {
                    ImageIcon hr = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_r.png")));
                    image = hr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    song.setFavorite(true);
                }
                heartLabel.setIcon(new ImageIcon(image));
            } catch (IOException err) {
                err.printStackTrace();
            }
            this.centerPanel.getFileManager().update();
            //TODO: remove the song from center panel if its being removed while in favorite playlist
            this.centerPanel.paint();
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
