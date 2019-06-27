package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {
    public EntityPanel(CenterPanel centerPanel, Entity entity) {
        super();
//        this.setPreferredSize(new Dimension(new Dimension(
//                centerPanel.getSize().width/3, centerPanel.getSize().height/2)));
        this.setPreferredSize(new Dimension(new Dimension(
                GUI.dim.width/8, GUI.dim.height/8)));

        JButton imageButton = new EntityPanelsImageButton(entity.getImageData(), centerPanel, entity);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(GUI.bgColorBlack);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(entity.getTitle());
        JLabel captionLabel = new JLabel(entity.getCaption());
        titleLabel.setForeground(Color.WHITE);
        captionLabel.setForeground(GUI.captionColorGrey);
        labelPanel.add(titleLabel);
        labelPanel.add(captionLabel);
        labelPanel.setBackground(GUI.bgColorBlack);
        subPanel.add(labelPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(new EntityPanelsRemovePanel(entity), BorderLayout.WEST);
        controlPanel.add(new EntityPanelsAddPanel(centerPanel, entity), BorderLayout.CENTER);
        controlPanel.add(new EntityPanelsHeartPanel(centerPanel.getFileManager(), entity), BorderLayout.EAST);
        subPanel.add(controlPanel, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(imageButton, BorderLayout.CENTER);
        this.add(subPanel, BorderLayout.SOUTH);
//        this.setMaximumSize(new Dimension(20, 30));
//        this.setMinimumSize(new Dimension(10, 15));
    }
}
