package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.FileManager;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private FileManager fileManager;
    private CenterPanel centerPanel;

    public LeftPanel(FileManager fileManager, CenterPanel centerPanel) {
        super();
        this.fileManager = fileManager;
        this.centerPanel = centerPanel;
//        this.setSize(new Dimension(100,200));
//        this.setMinimumSize(new Dimension(100,300));
        this.setPreferredSize(new Dimension(100, 200));
        this.setBackground(Color.RED);
        this.setLayout(new GridLayout(4, 1));

        this.add(new LeftPanelsAddPanel(fileManager, centerPanel));
        this.add(new LeftPanelsLibraryPanel(fileManager, centerPanel));
    }
}
