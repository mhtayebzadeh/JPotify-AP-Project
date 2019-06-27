package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class EntityPanelsAddRemovePanel extends JPanel {
    private FileManager fileManager;

    public EntityPanelsAddRemovePanel(FileManager fileManager) {
        super();
        this.fileManager = fileManager;
        this.setBackground(GUI.bgColorBlack);
        this.setLayout(new GridLayout(1, 2));

        JLabel addLabel = new JLabel();
        JLabel removeLabel = new JLabel();
        //addLabel.addMouseListener(new AddMouseListener());
        removeLabel.addMouseListener(new RemoveMouseListener());
        try {
            ImageIcon addIcon = new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/add and remove/add_icon.png")));
            ImageIcon removeIcon = new ImageIcon(ImageIO.read(new File(
                    "src/JPotifyGUI/images/add and remove/remove_icon.png")));
            Image addImage = addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Image removeImage = removeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            addLabel.setIcon(new ImageIcon(addImage));
            removeLabel.setIcon(new ImageIcon(removeImage));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        this.add(addLabel);
        this.add(removeLabel);
    }

    private class AddMouseListener implements MouseListener {
        private FileManager fileManager;

        public AddMouseListener(FileManager fileManager) {
            this.fileManager = fileManager;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            JPopupMenu popupMenu = new JPopupMenu("Select Playlist");
            for (Playlist playlist : this.fileManager.getPlaylists()) {
                JMenuItem playlistItem = new JMenuItem(playlist.getTitle());
                //playlistItem.addMouseListener();
                popupMenu.add(playlistItem);
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
