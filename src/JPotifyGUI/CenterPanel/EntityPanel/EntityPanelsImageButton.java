package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Player;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * this class is for the panel that the image of entities are shown
 * the panel is actually a button so performing an action can be felt
 */
public class EntityPanelsImageButton extends JButton {
    /**
     * @param centerPanel the main outer panel is needed
     *                    to both use and set its data
     * @param entity      each entity panel represents an entity and it is
     *                    needed for its data especially its image data here
     */
    public EntityPanelsImageButton(CenterPanel centerPanel, Entity entity) {
        super();
        this.setPreferredSize(new Dimension(200,200));
        try {
            ImageIcon bImageIcon;
            if (entity.getImageData() == null)
                bImageIcon = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/music_icon.png")));
            else {
                ByteArrayInputStream bis = new ByteArrayInputStream(entity.getImageData());
                bImageIcon = new ImageIcon(ImageIO.read(bis));
            }
            Image bImage = bImageIcon.getImage().getScaledInstance(
                    GUI.dim.width / 8, GUI.dim.width / 8, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(bImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBackground(GUI.bgColorBlack);
        this.addMouseListener(new PlaySongMouseListener(centerPanel, entity));
    }

    /**
     * a mouse listener class for when a music is selected for playing
     * only the mouseClicked method is overridden
     */
    private class PlaySongMouseListener implements MouseListener {
        private CenterPanel centerPanel;
        private Entity entity;

        public PlaySongMouseListener(CenterPanel centerPanel, Entity entity) {
            this.centerPanel = centerPanel;
            this.entity = entity;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == null)
                return;
            if (this.entity instanceof Song) {
                this.centerPanel.getPlayer().setSong((Song) entity);
                this.centerPanel.getLeftPanel().setImageData(this.entity.getImageData());
                this.centerPanel.getFileManager().update();
                this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().setPlayer(this.centerPanel.getPlayer());
                this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().paint();
            } else {
                Player.setCurrentPlaylist((Playlist) entity);
                if (this.entity instanceof Album) {
                    this.centerPanel.setLibraryKind("Album");
                    this.centerPanel.setLibraryFromSongs(((Album) entity).getSongs());
                }
                else if (this.entity instanceof Artist) {
                    this.centerPanel.setLibraryKind("Artist");
                    this.centerPanel.setLibraryFromSongs(((Artist) entity).getSongs());
                }
                else {
                    this.centerPanel.setLibraryKind(entity.getTitle());
                    this.centerPanel.setLibraryFromSongs(((Playlist) entity).getSongs());
                }
//                centerPanel.getLeftPanel().setImageData(((Playlist) entity).getSongs().get(0).getImageData());
            }
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
