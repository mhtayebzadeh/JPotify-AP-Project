package JPotifyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {
    public BottomPanel()
    {
        super();
        JButton playButton = new JButton("play");
        this.setLayout(new BorderLayout());
        this.add(playButton,BorderLayout.CENTER);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("play pressed at " + e.getWhen());
            }
        });
        this.setVisible(true);

    }


}
