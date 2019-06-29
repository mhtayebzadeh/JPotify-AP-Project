package JPotifyGUI.TopPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class TopPanel extends JPanel {
    JTextField searchField = new JTextField(20);
    CenterPanel centerPanel;
    JLabel searchText = new JLabel("Search");
    public TopPanel(CenterPanel centerPanel)
    {
        super();
        this.centerPanel = centerPanel;
        this.setBackground(GUI.bgColorBlack);
        this.setPreferredSize(new Dimension(-1,GUI.dim.height/20));
//        searchField.setBorder( BorderFactory.createCompoundBorder( new RoundField.RoundedBorder(), searchField.getBorder() ) );
        searchField.setPreferredSize(new Dimension(GUI.dim.width/10,GUI.dim.height/30));
        searchField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        searchField.setBackground(GUI.bgColorBlack);
        searchField.setForeground(GUI.bgColorBlack);


        searchField.getDocument().addDocumentListener(new DocumentListener() {
            Playlist p;
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {

//                System.out.println("........");
                if(searchField.getText().length()>2) {
                    System.out.println(searchField.getText());
                    p = FileManager.searchInSongs(searchField.getText());
                    System.out.println(p.getSongs().size());

                    centerPanel.setLibraryFromSongs(p.getSongs());
                    centerPanel.setLibraryKind("Songs");
                    centerPanel.paint();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                if(searchField.getText().length()>2) {
                    System.out.println(searchField.getText());
                    p = FileManager.searchInSongs(searchField.getText());
                    System.out.println(p.getSongs().size());

                    centerPanel.setLibraryFromSongs(p.getSongs());
                    centerPanel.setLibraryKind("Songs");
                    centerPanel.paint();
                }

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {


            }
        });

        this.add(searchText);
        this.add(searchField);
    }

}
