package JPotifyGUI.CenterPanel;

import JPotifyLogic.Entity.Entity;

import javax.swing.*;
import java.awt.*;

public class EntityPanel extends JPanel {
    private static final Color bgColorBlack = new Color(43, 43, 43);
    private static final Color captionColorGrey = new Color(180, 180, 180);

    public EntityPanel(CenterPanel centerPanel, Entity entity) {
        super();
        JButton imageButton = new EntityPanelsImageButton(entity.getImageData(), centerPanel, entity);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(bgColorBlack);
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(entity.getTitle());
        JLabel captionLabel = new JLabel(entity.getCaption());
        titleLabel.setForeground(Color.WHITE);
        captionLabel.setForeground(captionColorGrey);
        labelPanel.add(titleLabel);
        labelPanel.add(captionLabel);
        labelPanel.setBackground(bgColorBlack);
        subPanel.add(labelPanel, BorderLayout.CENTER);
        subPanel.add(new EntityPanelsHeartPanel(entity), BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(imageButton, BorderLayout.CENTER);
        this.add(subPanel, BorderLayout.SOUTH);
//        this.setMaximumSize(new Dimension(20, 30));
//        this.setMinimumSize(new Dimension(10, 15));
    }
}
