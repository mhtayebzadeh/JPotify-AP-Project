package JPotifyGUI.CenterPanel.EntityPanel.SubPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * this class is created for the little remove sign (minus sign)
 * on the bottom right corner of the entities
 * clicking it on songs removes the song from the whole song library
 * clicking it on playlists removes that playlist only but keeps the songs in library
 */
public class EntityPanelsRemovePanel extends JPanel {
    /**
     * @param centerPanel the outer larger center panel is needed to
     *                    both use and set its data in this panel
     * @param entity      every entity panel represents an entity which is
     *                    needed here to remove it from the right library
     */
    public EntityPanelsRemovePanel(CenterPanel centerPanel, Entity entity) {
        super();
        this.setBackground(GUI.bgColorBlack);

        if (!(entity instanceof Album || entity instanceof Artist)) {
            JLabel removeLabel = new JLabel();
            removeLabel.addMouseListener(new RemoveMouseListener(centerPanel, entity));
            try {
                ImageIcon removeIcon = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/add and remove/remove_icon.png")));
                Image removeImage = removeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                removeLabel.setIcon(new ImageIcon(removeImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.add(removeLabel);
        }
    }

    /**
     * this mouse listener is implemented to perform the correct action
     * when the little minus sign is clicked
     */
    private class RemoveMouseListener implements MouseListener {
        private CenterPanel centerPanel;
        private Entity entity;

        public RemoveMouseListener(CenterPanel centerPanel, Entity entity) {
            this.centerPanel = centerPanel;
            this.entity = entity;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int selection = JOptionPane.showConfirmDialog(
                    this.centerPanel, "Are you sure?");
            if (selection == JOptionPane.YES_OPTION) {
                if (this.entity instanceof Song) {
                    Song song = (Song) this.entity;
                    this.centerPanel.getFileManager().getSongs().remove(song);
                    this.centerPanel.setLibraryFromSongs(this.centerPanel.getFileManager().getSongs());
                } else if (this.entity instanceof Playlist) {
                    Playlist playlist = (Playlist) this.entity;
                    this.centerPanel.getFileManager().getPlaylists().remove(playlist);
                    this.centerPanel.setLibraryFromPlaylists(this.centerPanel.getFileManager().getPlaylists());
                    this.centerPanel.getLeftPanel().getLeftPanelsPlaylistsPanel().paint();
                }
                this.centerPanel.getFileManager().update();
                this.centerPanel.paint();
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
}
