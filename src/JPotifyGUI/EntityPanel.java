package JPotifyGUI;

import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.Playlist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class EntityPanel extends JPanel {
    private JButton imageButton;
    private CenterPanel centerPanel;
    private Entity entity;
    private static final Color bgColorBlack = new Color(43, 43, 43);
    private static final Color captionColorGrey = new Color(180, 180, 180);

    public EntityPanel(String title, String caption, byte[] imageData,
                       CenterPanel centerPanel, Entity entity, LeftPanel leftPanel) {
        super();
        this.imageButton = new JButton();
        this.centerPanel = centerPanel;
        this.entity = entity;

        this.setLayout(new BorderLayout());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        try {
            ImageIcon bImageIcon = new ImageIcon(ImageIO.read(bis));
            Image bImage = bImageIcon.getImage().getScaledInstance(
                    dim.width/8, dim.width/8, Image.SCALE_SMOOTH);
            this.imageButton.setIcon(new ImageIcon(bImage));
            // this.imageButton.setLocation(dim.width/3, dim.height/3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.imageButton.setBackground(bgColorBlack);
        this.imageButton.addMouseListener(new PlaySongMouseListener(this.centerPanel, this.entity));
        this.add(this.imageButton, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(title);
        JLabel captionLabel = new JLabel(caption);
        titleLabel.setForeground(Color.WHITE);
        captionLabel.setForeground(captionColorGrey);
        labelPanel.add(titleLabel);
        labelPanel.add(captionLabel);
        labelPanel.setBackground(bgColorBlack);
        subPanel.add(labelPanel, BorderLayout.CENTER);

        JPanel heartPanel = new JPanel();
        JLabel heartLabel = new JLabel();
        heartLabel.addMouseListener(new HeartMouseListener(this.entity, this.centerPanel));
        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_w.png")));
            Image image = ii.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            heartLabel.setIcon(new ImageIcon(image));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        heartPanel.add(heartLabel);
        subPanel.add(heartPanel, BorderLayout.EAST);

        subPanel.setBackground(bgColorBlack);
        this.add(subPanel, BorderLayout.SOUTH);
//        this.setMaximumSize(new Dimension(20, 30));
//        this.setMinimumSize(new Dimension(10, 15));
    }

    public class PlaySongMouseListener implements MouseListener {
        private CenterPanel centerPanel;
        private Entity entity;
        private LeftPanel leftPanel;

        public PlaySongMouseListener(CenterPanel centerPanel, Entity entity) {
            this.centerPanel = centerPanel;
            this.entity = entity;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == null)
                return;

            JButton button = (JButton) e.getSource();
            if (this.entity instanceof Song) {
                this.centerPanel.getPlayer().setSong((Song) entity);
                this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().setPlayer(this.centerPanel.getPlayer());
                this.centerPanel.getBottomPanel().getBottomPanelsCurrentMusicPanel().paint();

                this.centerPanel.getLeftPanel().setImageData(this.entity.getImageData());
            }

            else {
                this.centerPanel.getPlayer().setPlayList((Playlist) entity);
                if (this.entity instanceof Album)
                    this.centerPanel.setLibraryFromSongs(((Album) entity).getSongs());
                else if (this.entity instanceof Artist)
                    this.centerPanel.setLibraryFromSongs(((Artist) entity).getSongs());
                else
                    this.centerPanel.setLibraryFromSongs(((Playlist) entity).getSongs());
                this.centerPanel.paint();
            }
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

    private class HeartMouseListener implements MouseListener {
        private Entity entity;

        public HeartMouseListener(Entity entity, CenterPanel centerPanel) {
            this.entity = entity;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel heartLabel = (JLabel) e.getSource();
            Song song = (Song) this.entity;

            try {
                ImageIcon hw = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_w.png")));
                ImageIcon hr = new ImageIcon(ImageIO.read(new File("src/JPotifyGUI/images/heart/heart_tr_r.png")));
                Image image;
                if (song.isFavorite()) {
                    image = hr.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    song.setFavorite(false);
                }
                else {
                    image = hw.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    song.setFavorite(true);
                    //this.
                }
                heartLabel.setIcon(new ImageIcon(image));
            } catch (IOException err) {
                err.printStackTrace();
            }
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
