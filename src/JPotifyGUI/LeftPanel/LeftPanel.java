package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.LogicData;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private LogicData logicData;
    private CenterPanel centerPanel;

    public LeftPanel(LogicData logicData, CenterPanel centerPanel) {
        super();
        this.logicData = logicData;
        this.centerPanel = centerPanel;
//        this.setSize(new Dimension(100,200));
//        this.setMinimumSize(new Dimension(100,300));
        this.setPreferredSize(new Dimension(100, 200));
        this.setBackground(Color.RED);
        this.setLayout(new GridLayout(4, 1));

        this.add(new LeftPanelsAddPanel(logicData, centerPanel));
        this.add(new LeftPanelsLibraryPanel(logicData, centerPanel));
    }
}
