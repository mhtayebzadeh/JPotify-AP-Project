package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * this panel class is for the left side of the GUI
 * and its inner components which are classes themselves
 */
public class LeftPanel extends JPanel {
    private CenterPanel centerPanel;
    private LeftPanelsPlaylistsPanel leftPanelsPlaylistsPanel;
    private LeftPanelsImagePanel imagePanel;

    /**
     * @param centerPanel only needs access to centerPanel in order to set it when
     *                    items from add panel or any of the 2 lists panel is chosen
     */
    public LeftPanel(CenterPanel centerPanel) {
        super();
        this.centerPanel = centerPanel;
        this.setPreferredSize(new Dimension(GUI.dim.width / 8, GUI.dim.height / 8));
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.sideColorBlack);

        this.leftPanelsPlaylistsPanel = new LeftPanelsPlaylistsPanel(this);
        JPanel leftScrollPanel = new JPanel();
        leftScrollPanel.setLayout(new BorderLayout());
        JScrollPane leftScrollPane = new JScrollPane(leftScrollPanel);
        leftScrollPanel.add(new LeftPanelsLibraryPanel(this), BorderLayout.NORTH);
        leftScrollPanel.add(this.leftPanelsPlaylistsPanel, BorderLayout.CENTER);

        this.imagePanel = new LeftPanelsImagePanel();

        this.add(new LeftPanelsAddPanel(this), BorderLayout.NORTH);
        this.add(leftScrollPane, BorderLayout.CENTER);
        this.add(this.imagePanel, BorderLayout.SOUTH);
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public FileManager getFileManager() {
        return centerPanel.getFileManager();
    }

    public LeftPanelsPlaylistsPanel getLeftPanelsPlaylistsPanel() {
        return leftPanelsPlaylistsPanel;
    }

    /**
     * @param imageData gets the image data bytes and sets them in left bottom panel
     *                  is used when the song is changed
     */
    public void setImageData(byte[] imageData) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.imagePanel.removeAll();
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(dim.width / 8, dim.width / 8));
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        try {
            ImageIcon bImageIcon = new ImageIcon(ImageIO.read(bis));
            Image bImage = bImageIcon.getImage().getScaledInstance(
                    dim.width / 8, dim.width / 8, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(bImage));
        } catch (IOException err) {
            err.printStackTrace();
        }
        this.imagePanel.add(imageLabel);
        this.add(imagePanel, BorderLayout.SOUTH);
        this.revalidate();
    }
}