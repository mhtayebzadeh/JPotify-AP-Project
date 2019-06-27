package JPotifyGUI;

import JPotifyGUI.TopPanel.SearchPanel;
import JPotifyGUI.TopPanel.TopPanel;

import javax.swing.*;

public class guiTest {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("test");
        SearchPanel searchPanel = new SearchPanel();
        frame.add(searchPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
