package JPotifyGUI.LeftPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class LeftPanel extends JPanel {
    private CenterPanel centerPanel;
    private LeftPanelsPlaylistsPanel leftPanelsPlaylistsPanel;
    private JPanel imagePanel;
    private byte[] imageData;

    public LeftPanel(CenterPanel centerPanel) {
        super();
        this.centerPanel = centerPanel;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, dim.height/8));
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.sideColorBlack);

        this.leftPanelsPlaylistsPanel = new LeftPanelsPlaylistsPanel(centerPanel.getFileManager());
        JPanel leftScrollPanel = new JPanel();
        leftScrollPanel.setLayout(new BorderLayout());
        JScrollPane leftScrollPane = new JScrollPane(leftScrollPanel);
        leftScrollPanel.add(new LeftPanelsLibraryPanel(this), BorderLayout.NORTH);
        leftScrollPanel.add(this.leftPanelsPlaylistsPanel, BorderLayout.CENTER);

        this.imagePanel =  new JPanel();
        this.imagePanel.setPreferredSize(new Dimension(dim.width/8, dim.width/8));
        this.imagePanel.setBackground(GUI.sideColorBlack);
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/music_icon.png")));
            Image image = ii.getImage().getScaledInstance(dim.width/8, dim.width/8, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        this.imagePanel.setBackground(GUI.sideColorBlack);
        this.imagePanel.add(imageLabel);

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

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.imagePanel.removeAll();
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(dim.width/8, dim.width/8));
        ByteArrayInputStream bis = new ByteArrayInputStream(this.imageData);
        try {
            ImageIcon bImageIcon = new ImageIcon(ImageIO.read(bis));
            Image bImage = bImageIcon.getImage().getScaledInstance(
                    dim.width/8, dim.width/8, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(bImage));
        } catch (IOException err) {
            err.printStackTrace();
        }
        imagePanel.add(imageLabel);
        this.add(imagePanel, BorderLayout.SOUTH);
        this.revalidate();
    }
}