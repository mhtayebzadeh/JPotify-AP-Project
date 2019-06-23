package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeftPanelsLibraryPanel extends JPanel {
    private JList<String> libraryList;
    private CenterPanel centerPanel;
    private FileManager fileManager;

    public LeftPanelsLibraryPanel(FileManager fileManager, CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
        this.fileManager = fileManager;

        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("Songs");
        list.addElement("Albums");
        list.addElement("Artists");
        list.addElement("Playlists");
        this.libraryList = new JList<>(list);
        this.libraryList.addMouseListener(new LibraryMouseListener(fileManager, this.libraryList, centerPanel));
        this.centerPanel.setLibraryFromSongs(this.fileManager.getSongs());
        this.centerPanel.paint();

        this.setLayout(new GridLayout(1, 1));
        this.add(this.libraryList);
    }

    public class LibraryMouseListener implements MouseListener {
        private JList<String> libraryList;
        private CenterPanel centerPanel;
        private FileManager fileManager;

        public LibraryMouseListener(FileManager fileManager, JList<String> libraryList, CenterPanel centerPanel) {
            this.libraryList = libraryList;
            this.centerPanel = centerPanel;
            this.fileManager = fileManager;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (this.libraryList.getSelectedIndex() != -1) {
                String selectedValue = this.libraryList.getSelectedValue();
                if (selectedValue.equals("Songs"))
                    this.centerPanel.setLibraryFromSongs(this.fileManager.getSongs());
                else if (selectedValue.equals("Playlists"))
                    this.centerPanel.setLibraryFromPlaylists(this.fileManager.getPlaylists());
                else if (selectedValue.equals("Albums"))
                    this.centerPanel.setLibraryFromAlbums(this.fileManager.getAlbums());
                else if (selectedValue.equals("Artists"))
                    this.centerPanel.setLibraryFromArtists(this.fileManager.getArtists());
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
