package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel;
import JPotifyLogic.FileManager;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private FileManager fileManager;
    private CenterPanel centerPanel;
    private LeftPanelsPlaylistsPanel leftPanelsPlaylistsPanel;
    private byte[] imageData;
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
        this.setLayout(new BorderLayout());
        this.setBackground(sideColorBlack);

        this.leftPanelsPlaylistsPanel = new LeftPanelsPlaylistsPanel(fileManager);
        JPanel leftScrollPanel = new JPanel();
        leftScrollPanel.setLayout(new BorderLayout());
        JScrollPane leftScrollPane = new JScrollPane(leftScrollPanel);
        leftScrollPanel.add(new LeftPanelsLibraryPanel(this), BorderLayout.NORTH);
        leftScrollPanel.add(this.leftPanelsPlaylistsPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(dim.width/8, dim.width/8));

        this.add(new LeftPanelsAddPanel(this), BorderLayout.NORTH);
        this.add(leftScrollPane, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public LeftPanelsPlaylistsPanel getLeftPanelsPlaylistsPanel() {
        return leftPanelsPlaylistsPanel;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
