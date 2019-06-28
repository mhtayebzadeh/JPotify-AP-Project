package JPotifyGUI.RightPanel;

import JPotifyGUI.GUI;
import JPotifyLogic.Network.Friend;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class RightPanelsAddPanel extends JPanel {
    public RightPanelsAddPanel(RightPanel rightPanel) {
        super();
        this.setLayout(new GridLayout());

        JPanel newFriendPanel = new JPanel();
        newFriendPanel.setBackground(GUI.sideColorBlack);
        BorderLayout b = new BorderLayout();
        b.setHgap(10);
        newFriendPanel.setLayout(b);
        JLabel newFriendButton = new JLabel();
        newFriendButton.setBackground(GUI.sideColorBlack);
        newFriendButton.addMouseListener(new NewFriendMouseListener(rightPanel));
        JLabel newFriendLabel = new JLabel("New Friend");
        newFriendLabel.setForeground(GUI.captionColorGrey);

        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/add and remove/add_icon.png")));
            Image image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            newFriendButton.setIcon(new ImageIcon(image));
            newFriendButton.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        newFriendPanel.add(newFriendButton, BorderLayout.WEST);
        newFriendPanel.add(newFriendLabel, BorderLayout.CENTER);
        this.add(newFriendPanel);
    }

    private class NewFriendMouseListener implements MouseListener {
        private RightPanel rightPanel;

        public NewFriendMouseListener(RightPanel rightPanel) {
            this.rightPanel = rightPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTextField nameField = new JTextField(10);
            JTextField addressField = new JTextField(10);

            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new GridLayout(2, 1));
            optionPanel.add(new JLabel("Enter friend's name: "));
            optionPanel.add(nameField);
            optionPanel.add(new JLabel("Enter friend's IP address: "));
            optionPanel.add(addressField);

            int result = JOptionPane.showConfirmDialog(null, optionPanel,
                    "Please enter friend's information", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                    this.rightPanel.getNetworkManager().addFriend(
                            new Friend(nameField.getText(), addressField.getText()));

            }
            this.rightPanel.getRightPanelsFriendsPanel().paint();
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
