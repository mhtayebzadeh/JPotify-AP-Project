package JPotifyGUI.CenterPanel.EntityPanel.SubPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * the little add panel un the bottom right corner of the songs
 * by clicking on these panels the user can add songs to the playlists
 */
public class EntityPanelsAddPanel extends JPanel {
    /**
     * @param centerPanel the larger outer center panel object. its needed to use
     *                    and also set its data when adding songs to playlists
     * @param entity      each entity panel represents an entity
     */
    public EntityPanelsAddPanel(CenterPanel centerPanel, Entity entity) {
        super();
        this.setBackground(GUI.bgColorBlack);

        if (entity instanceof Song) {
            JLabel addLabel = new JLabel();
            addLabel.addMouseListener(new AddMouseListener(centerPanel, entity));
            try {
                ImageIcon addIcon = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/add and remove/add_icon.png")));
                Image addImage = addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                addLabel.setIcon(new ImageIcon(addImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.add(addLabel);
        }
    }

    /**
     * a mouse listener class to add songs to playlists
     * when the little plus button is clicked
     * only the mouseClicked method is overridden
     */
    private class AddMouseListener implements MouseListener {
        private CenterPanel centerPanel;
        private Entity entity;

        public AddMouseListener(CenterPanel centerPanel, Entity entity) {
            this.centerPanel = centerPanel;
            this.entity = entity;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JPopupMenu popupMenu = new JPopupMenu("Select Playlist");
            if (this.centerPanel.getFileManager().getPlaylists().size() == 0) {
                JMenuItem menuItem = new JMenuItem("No Playlists Available!");
                popupMenu.add(menuItem);
            }
            for (Playlist playlist : this.centerPanel.getFileManager().getPlaylists()) {
                JMenuItem playlistItem = new JMenuItem(playlist.getTitle());

                // intellij offered this lambda function!
                playlistItem.addActionListener(e1 -> {
                    Song song = (Song) entity;
                    playlist.addSong(song);
                    playlist.setCaption(song.getCaption());
                    playlist.setImageData(song.getImageData());
                });
                popupMenu.add(playlistItem);
            }
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
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
