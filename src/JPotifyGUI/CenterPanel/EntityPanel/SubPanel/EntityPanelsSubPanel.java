package JPotifyGUI.CenterPanel.EntityPanel.SubPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;

import javax.swing.*;
import java.awt.*;

/**
 * this class is created as the sub panel of each entity panel
 * it contains the entity's data
 * it may also contain add, remove, like/dislike button if the entity is song
 * or just the remove button if the entity is playlist
 * or no button at all and just the data if the entity is an album or an artist
 */
public class EntityPanelsSubPanel extends JPanel {
    public EntityPanelsSubPanel(CenterPanel centerPanel, Entity entity) {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.bgColorBlack);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(entity.getTitle());
        JLabel captionLabel = new JLabel(entity.getCaption());
        titleLabel.setForeground(Color.WHITE);
        captionLabel.setForeground(GUI.captionColorGrey);
        labelPanel.add(titleLabel);
        labelPanel.add(captionLabel);
        labelPanel.setBackground(GUI.bgColorBlack);
        this.add(labelPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(new EntityPanelsRemovePanel(centerPanel, entity), BorderLayout.WEST);
        controlPanel.add(new EntityPanelsAddPanel(centerPanel, entity), BorderLayout.CENTER);
        controlPanel.add(new EntityPanelsHeartPanel(centerPanel, entity), BorderLayout.EAST);
        this.add(controlPanel, BorderLayout.EAST);
    }
}
