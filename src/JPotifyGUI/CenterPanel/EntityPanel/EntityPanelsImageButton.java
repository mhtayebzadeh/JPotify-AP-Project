package JPotifyGUI.CenterPanel.EntityPanel;

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
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class EntityPanelsImageButton extends JButton {
    public EntityPanelsImageButton(byte[] imageData, CenterPanel centerPanel, Entity entity) {
        super();
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        try {
            ImageIcon bImageIcon = new ImageIcon(ImageIO.read(bis));
            Image bImage = bImageIcon.getImage().getScaledInstance(
                    GUI.dim.width/8, GUI.dim.width/8, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(bImage));
            // this.imageButton.setLocation(dim.width/3, dim.height/3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBackground(GUI.bgColorBlack);
        this.addMouseListener(new PlaySongMouseListener(centerPanel, entity));
    }

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
            }
            else {
                this.centerPanel.getPlayer().setCurrentPlaylist((Playlist) entity);
                if (this.entity instanceof Album)
                    this.centerPanel.setLibraryFromSongs(((Album) entity).getSongs());
                else if (this.entity instanceof Artist)
                    this.centerPanel.setLibraryFromSongs(((Artist) entity).getSongs());
                else {
                    this.centerPanel.setLibraryFromSongs(((Playlist) entity).getSongs());
                }
//                centerPanel.getLeftPanel().setImageData(((Playlist) entity).getSongs().get(0).getImageData());
                this.centerPanel.paint();
            }
            this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().setPlayer(this.centerPanel.getPlayer());
            this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().paint();
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
