package JPotifyGUI.CenterPanel.EntityPanel;

import JPotifyGUI.CenterPanel.CenterPanel;
import JPotifyGUI.CenterPanel.EntityPanel.SubPanel.EntityPanelsSubPanel;
import JPotifyGUI.GUI;
import JPotifyLogic.Entity.Entity;

import javax.swing.*;
import java.awt.*;

/**
 * this class is implemented as the center panel's inner components
 * entity panels are made up of entities, which can be songs or playlists
 * <p>
 * this panel is in the center of attention and it has multiple of listeners
 * so its components are divided to several panel classes themselves
 */
public class EntityPanel extends JPanel {
    /**
     * @param centerPanel the outer panel of these smaller components
     *                    is needed for them in order to get other data from it
     *                    to use them or set them
     * @param entity      each entity panel represents an entity, whose data is needed
     */
    public EntityPanel(CenterPanel centerPanel, Entity entity) {
        super();
        this.setPreferredSize(new Dimension(new Dimension(
                GUI.dim.width / 8, GUI.dim.height / 8)));
        this.setMaximumSize(new Dimension(new Dimension(
                GUI.dim.width / 8, GUI.dim.height / 8)));
        this.setLayout(new BorderLayout());

        JButton imageButton = new EntityPanelsImageButton(centerPanel, entity);
        EntityPanelsSubPanel entityPanelsSubPanel = new EntityPanelsSubPanel(centerPanel, entity);

        this.add(imageButton, BorderLayout.CENTER);
        this.add(entityPanelsSubPanel, BorderLayout.SOUTH);

    }
}
