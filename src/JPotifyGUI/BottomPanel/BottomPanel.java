package JPotifyGUI.BottomPanel;

import JPotifyLogic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {
    private Player player;

    public BottomPanel(Player player) {
        super();
        this.player = player;
        this.setLayout(new BorderLayout());
        this.add(new BottomPanelsMusicControlPanel(), BorderLayout.CENTER);
        this.add(new BottomPanelsCurrentMusicPanel(player), BorderLayout.WEST);

        /*this.add(playButton, BorderLayout.CENTER);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("play pressed at " + e.getWhen());
            }
        });*/
    }


}
