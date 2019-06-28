package JPotifyGUI.CenterPanel;

import JPotifyGUI.BottomPanel.BottomPanel;
import JPotifyGUI.CenterPanel.EntityPanel.EntityPanel;
import JPotifyGUI.GUI;
import JPotifyGUI.LeftPanel.LeftPanel;
import JPotifyLogic.Entity.Entity;
import JPotifyLogic.Entity.Song;
import JPotifyLogic.FileManager;
import JPotifyLogic.Player;
import JPotifyLogic.Playlist.Album;
import JPotifyLogic.Playlist.Artist;
import JPotifyLogic.Playlist.Playlist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * this class is written as GUI's center panel (the main panel)
 * and its components
 */
public class CenterPanel extends JPanel {
    private ArrayList<Entity> library;
    private ArrayList<EntityPanel> entityPanels;
    private String libraryKind;
    private Player player;
    private BottomPanel bottomPanel;
    private LeftPanel leftPanel;
    private FileManager fileManager;

    /**
     * @param player      is required to be able to play musics from the main panel
     * @param fileManager all the fields from logic are gathered in this object
     *                    so it's needed to perform all the needed actions
     * @param bottomPanel is required because making choices in center panel,
     *                    causes changes to bottom panel as well
     * @param leftPanel   is required because changes in center panel,
     *                    causes change to left panel as well
     */
    public CenterPanel(Player player, FileManager fileManager, BottomPanel bottomPanel, LeftPanel leftPanel) {
        super();
        this.setBackground(GUI.bgColorBlack);
        this.entityPanels = new ArrayList<>();
        this.library = new ArrayList<>();
        this.libraryKind = "Songs";
        this.setLayout(new GridLayout(0, 3));
        this.player = player;
        this.fileManager = fileManager;
        this.bottomPanel = bottomPanel;
        this.leftPanel = leftPanel;
    }

    /**
     * when changes in other places occur that involves this panel,
     * it's components are repainted using this method
     */
    public void paint() {
        this.removeAll();
        this.resetLibrary();
        this.fileManager.update();
        this.entityPanels = new ArrayList<>();
        for (Entity entity : this.library) {
            EntityPanel entityPanel = new EntityPanel(this, entity);
            this.entityPanels.add(entityPanel);
            this.add(entityPanel);
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * resets the center panel's library using it's current library's kind
     */
    // TODO: can be used in many places of gui instead of using the setLibrary methods
    // written very late and near to deadline
    private void resetLibrary() {
        switch (this.libraryKind) {
            case "Songs":
                this.setLibraryFromSongs(this.fileManager.getSongs());
                break;
            case "Albums":
                this.setLibraryFromAlbums(this.fileManager.getAlbums());
                break;
            case "Artists":
                this.setLibraryFromArtists(this.fileManager.getArtists());
                break;
            case "Playlists":
                this.setLibraryFromPlaylists(this.fileManager.getPlaylists());
                break;
            case "Favorite Playlist":
                this.setLibraryFromSongs(this.fileManager.getFavoritePlaylist().getSongs());
                break;
            case "Shared Playlist":
                this.setLibraryFromSongs(FileManager.getSharedPlaylist().getSongs());
                break;
            default:
                this.setLibraryFromSongs(this.fileManager.getPlaylistFromName(this.libraryKind).getSongs());
                break;
        }
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public String getLibraryKind() {
        return libraryKind;
    }

    public void setLibraryKind(String libraryKind) {
        this.libraryKind = libraryKind;
    }

    /**
     * @param songs gets an arrayList of songs and sets this
     *              panel's displaying components with them
     */
    public void setLibraryFromSongs(ArrayList<Song> songs) {
        this.library = new ArrayList<>();
        this.library.addAll(songs);
    }

    /**
     * @param playlists gets an arrayList of playlists and sets this
     *                  panel's displaying components with them
     */
    public void setLibraryFromPlaylists(ArrayList<Playlist> playlists) {
        this.library = new ArrayList<>();
        this.library.addAll(playlists);
    }

    /**
     * @param albums gets an arrayList of albums and sets this
     *               panel's displaying components with them
     */
    public void setLibraryFromAlbums(ArrayList<Album> albums) {
        this.library = new ArrayList<>();
        this.library.addAll(albums);
    }

    /**
     * @param artists gets an arrayList of artists and sets this
     *                panel's displaying components with them
     */
    public void setLibraryFromArtists(ArrayList<Artist> artists) {
        this.library = new ArrayList<>();
        this.library.addAll(artists);
    }
}