package JPotifyGUI;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    public RightPanel() {
        super();
//        this.setSize(200,300);
//        this.setMinimumSize(new Dimension(200,300));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/8, dim.height/8));
        this.setBackground(Color.CYAN);
    }
}
