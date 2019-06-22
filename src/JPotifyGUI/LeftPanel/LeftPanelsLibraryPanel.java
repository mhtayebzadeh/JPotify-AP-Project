package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.LogicData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeftPanelsLibraryPanel extends JPanel {
    private JList<String> libraryList;
    private CenterPanel centerPanel;
    private LogicData logicData;

    public LeftPanelsLibraryPanel(LogicData logicData, CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
        this.logicData = logicData;

        DefaultListModel<String> list = new DefaultListModel<>();
        list.addElement("Songs");
        list.addElement("Albums");
        list.addElement("Artists");
        this.libraryList = new JList<>(list);
        this.libraryList.addMouseListener(new LibraryMouseListener(logicData, this.libraryList, centerPanel));
        this.centerPanel.setLibraryFromSongs(this.logicData.getSongs());

        this.setLayout(new GridLayout(1, 1));
        this.add(this.libraryList);
    }

    public class LibraryMouseListener implements MouseListener {
        private JList<String> libraryList;
        private CenterPanel centerPanel;
        private LogicData logicData;

        public LibraryMouseListener(LogicData logicData, JList<String> libraryList, CenterPanel centerPanel) {
            this.libraryList = libraryList;
            this.centerPanel = centerPanel;
            this.logicData = logicData;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (this.libraryList.getSelectedIndex() != -1) {
                if (libraryList.getSelectedValue().equals("Songs"))
                    this.centerPanel.setLibraryFromSongs(this.logicData.getSongs());
                else if (libraryList.getSelectedValue().equals("Playlists"))
                    this.centerPanel.setLibraryFromPlaylists(this.logicData.getPlaylists());
            }
            this.centerPanel.setLibraryFromSongs(this.logicData.getSongs());
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
