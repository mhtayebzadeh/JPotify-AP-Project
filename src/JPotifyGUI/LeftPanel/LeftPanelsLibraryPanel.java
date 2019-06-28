package JPotifyGUI.LeftPanel;

import JPotifyGUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * this panel class is for the left side library panel
 * for which the constant items named below are always displayed
 * Songs, Albums, Artists and Playlists
 */
public class LeftPanelsLibraryPanel extends JPanel {
    /**
     * @param leftPanel this is needed to gain access to fileManager object
     *                  and also add playlists to bottom playlists list when
     *                  the above add playlist button is clicked
     */
    public LeftPanelsLibraryPanel(LeftPanel leftPanel) {
        super();
        this.setBackground(GUI.sideColorBlack);
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Library");
        label.setForeground(GUI.bottomColorBlack);
        label.setPreferredSize(new Dimension(30, 30));

        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("Songs");
        list.addElement("Albums");
        list.addElement("Artists");
        list.addElement("Playlists");
        JList<String> libraryList = new JList<>(list);
        libraryList.addMouseListener(new LibraryMouseListener(leftPanel, libraryList));
        libraryList.setBackground(GUI.sideColorBlack);
        libraryList.setForeground(GUI.captionColorGrey);
        leftPanel.getCenterPanel().setLibraryFromSongs(leftPanel.getFileManager().getSongs());
        leftPanel.getCenterPanel().paint();

        this.add(label, BorderLayout.NORTH);
        this.add(libraryList, BorderLayout.SOUTH);
    }

    /**
     * mouse listener for when an item from library list is selected
     * and proper arrayList should be set for the centerPanel
     * only the mouseClicked method is overridden here
     */
    private class LibraryMouseListener implements MouseListener {
        private JList<String> libraryList;
        private LeftPanel leftPanel;

        public LibraryMouseListener(LeftPanel leftPanel, JList<String> libraryList) {
            this.libraryList = libraryList;
            this.leftPanel = leftPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (this.libraryList.getSelectedIndex() != -1) {
                String selectedValue = this.libraryList.getSelectedValue();
                switch (selectedValue) {
                    case "Songs":
                        this.leftPanel.getCenterPanel().setLibraryFromSongs(this.leftPanel.getFileManager().getSongs());
                        break;
                    case "Playlists":
                        this.leftPanel.getCenterPanel().setLibraryFromPlaylists(this.leftPanel.getFileManager().getPlaylists());
                        break;
                    case "Albums":
                        this.leftPanel.getCenterPanel().setLibraryFromAlbums(this.leftPanel.getFileManager().getAlbums());
                        break;
                    case "Artists":
                        this.leftPanel.getCenterPanel().setLibraryFromArtists(this.leftPanel.getFileManager().getArtists());
                        break;
                }
                this.leftPanel.getCenterPanel().setLibraryKind(selectedValue);
                this.leftPanel.getCenterPanel().paint();
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
