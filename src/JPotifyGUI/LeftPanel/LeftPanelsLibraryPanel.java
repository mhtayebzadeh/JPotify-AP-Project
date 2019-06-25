package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeftPanelsLibraryPanel extends JPanel {
    private JList<String> libraryList;
    private LeftPanel leftPanel;
    private static final Color sideColorBlack = new Color(15, 15, 15);
    private static final Color captionColorGrey = new Color(180, 180, 180);
    private static final Color bottomColorBlack = new Color(100, 100, 100);

    public LeftPanelsLibraryPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
        this.setBackground(sideColorBlack);
        DefaultListModel<String> list = new DefaultListModel<>();

        list.addElement("Songs");
        list.addElement("Albums");
        list.addElement("Artists");
        list.addElement("Playlists");
        this.libraryList = new JList<>(list);
        this.libraryList.addMouseListener(new LibraryMouseListener(this.leftPanel, this.libraryList));
        this.libraryList.setBackground(sideColorBlack);
        this.libraryList.setForeground(captionColorGrey);
        this.leftPanel.getCenterPanel().setLibraryFromSongs(this.leftPanel.getFileManager().getSongs());
        this.leftPanel.getCenterPanel().paint();

        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("Library");
        label.setForeground(bottomColorBlack);
        label.setPreferredSize(new Dimension(30, 30));
        this.add(label, BorderLayout.NORTH);
        this.add(this.libraryList, BorderLayout.CENTER);
    }

    public class LibraryMouseListener implements MouseListener {
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
