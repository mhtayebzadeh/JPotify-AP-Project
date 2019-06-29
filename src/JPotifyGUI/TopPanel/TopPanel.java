package JPotifyGUI.TopPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TopPanel extends JPanel {
    private JTextField searchField = new JTextField(20);
    private FileManager fileManager;
    public TopPanel(CenterPanel centerPanel,FileManager fileManager) {
        super();
        this.setBackground(GUI.bgColorBlack);
        this.setPreferredSize(new Dimension(-1, GUI.dim.height / 20));
        this.fileManager = fileManager;
        this.searchField.setPreferredSize(new Dimension(GUI.dim.width / 10, GUI.dim.height / 30));
        this.searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.searchField.setBackground(GUI.sideColorBlack);
        this.searchField.setForeground(Color.RED);


        this.searchField.getDocument().addDocumentListener(new DocumentListener() {
            private Playlist p;

            @Override
            public void insertUpdate(DocumentEvent documentEvent) {

//                System.out.println("........");
                if (searchField.getText().length() > 2) {
                    System.out.println(searchField.getText());
                    p = FileManager.searchInSongs(searchField.getText());
                    System.out.println(p.getSongs().size());

                    centerPanel.setLibraryFromSongs(p.getSongs());
                    centerPanel.setLibraryKind("Search");
                    centerPanel.paint();
                }
                else {
                    centerPanel.setLibraryFromSongs(fileManager.getSongs());
                    centerPanel.setLibraryKind("Songs");
                    centerPanel.paint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                if (searchField.getText().length() > 2) {
                    System.out.println(searchField.getText());
                    p = FileManager.searchInSongs(searchField.getText());
                    System.out.println(p.getSongs().size());

                    centerPanel.setLibraryFromSongs(p.getSongs());
                    centerPanel.setLibraryKind("Search");
                    centerPanel.paint();
                }
                else {
                    centerPanel.setLibraryFromSongs(fileManager.getSongs());
                    centerPanel.setLibraryKind("Songs");
                    centerPanel.paint();
                }

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });

        JLabel searchText = new JLabel("Search");
        this.add(searchText);
        this.add(searchField);
    }

}
