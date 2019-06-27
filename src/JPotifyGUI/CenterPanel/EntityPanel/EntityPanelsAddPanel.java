package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class EntityPanelsAddPanel extends JPanel {

    public EntityPanelsAddPanel(CenterPanel centerPanel, Entity entity) {
        super();
        this.setBackground(GUI.bgColorBlack);

        if (entity instanceof Song) {
            Song song = (Song) entity;
            JLabel addLabel = new JLabel();
            addLabel.addMouseListener(new AddMouseListener(centerPanel, entity));
            try {
                ImageIcon addIcon = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/add and remove/add_icon.png")));
                Image addImage = addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                addLabel.setIcon(new ImageIcon(addImage));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            this.add(addLabel);
        }
    }

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

                playlistItem.addMouseListener(new SelectedPlaylistMouseListener(playlist, entity));
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

        private class SelectedPlaylistMouseListener implements MouseListener {
            private Playlist playlist;
            private Entity entity;

            public SelectedPlaylistMouseListener(Playlist playlist, Entity entity) {
                this.playlist = playlist;
                this.entity = entity;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Song song = (Song) this.entity;
                System.out.println(this.entity.getTitle());
                this.playlist.addSong(song);
                this.playlist.setCaption(song.getCaption());
                this.playlist.setImageData(song.getImageData());
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
}
