package JPotifyGUI.RightPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Network.Friend;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FriendPanel extends JPanel {
    public FriendPanel(Friend friend) {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.sideColorBlack);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GUI.sideColorBlack);

        JLabel namePanel = new JLabel(friend.getName());
        namePanel.setForeground(Color.WHITE);
        mainPanel.add(namePanel, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(GUI.sideColorBlack);
        if (friend.getLastArtwork() != null) {
            JLabel titleLabel = new JLabel(friend.getLastArtwork().getTitle());
            JLabel captionLabel = new JLabel(friend.getLastArtwork().getCaption());
            JLabel timeLabel = new JLabel(friend.getLastArtwork().getTimeLastPlayed());

            titleLabel.setForeground(GUI.captionColorGrey);
            captionLabel.setForeground(GUI.captionColorGrey);
            timeLabel.setForeground(GUI.captionColorGrey);

            subPanel.add(titleLabel, BorderLayout.NORTH);
            subPanel.add(captionLabel, BorderLayout.CENTER);
            subPanel.add(timeLabel, BorderLayout.SOUTH);
        }

        JLabel imageLabel = new JLabel();
        try {
            ImageIcon ch;
            if (friend.getStatus().equals("online"))
                ch = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/flat_icon/checked(1).png")));
            else
                ch = new ImageIcon(ImageIO.read(new File(
                        "src/JPotifyGUI/images/flat_icon/cancel.png")));
            Image image = ch.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
        } catch (IOException err) {
            err.printStackTrace();
        }

        mainPanel.add(subPanel, BorderLayout.SOUTH);
        mainPanel.add(imageLabel, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.NORTH);
    }
}