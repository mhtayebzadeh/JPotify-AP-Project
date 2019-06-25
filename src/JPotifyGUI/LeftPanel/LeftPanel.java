package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.FileManager;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private FileManager fileManager;
    private CenterPanel centerPanel;
    private static final Color sideColorBlack = new Color(15, 15, 15);

    public LeftPanel(FileManager fileManager, CenterPanel centerPanel) {
        super();
        this.fileManager = fileManager;
        this.centerPanel = centerPanel;
//        this.setSize(new Dimension(100,200));
//        this.setMinimumSize(new Dimension(100,300));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, dim.height/8));
        this.setBackground(Color.RED);
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(sideColorBlack);

        this.add(new LeftPanelsAddPanel(fileManager, centerPanel));
        this.add(new LeftPanelsLibraryPanel(fileManager, centerPanel));
    }
}
