package JPotifyLogic.Entity;

import java.io.Serializable;

/**
 * abstract class which holds the common information between
 * songs and playlists. this class is used in GUI entity panels
 * to be able to show both songs and playlists (polymorphism)
 */
public abstract class Entity implements Serializable {
    private String title;
    private String caption;
    private byte[] imageData;

    /**
     * default constructor (essential)
     */
    public Entity() {
    }

    /**
     * @param title     title of the entity (name)
     * @param caption   caption of the entity (whatever the developer desires)
     * @param imageData artwork of the entity (song image and whatever for playlists)
     */
    public Entity(String title, String caption, byte[] imageData) {
        this.title = title;
        this.caption = caption;
        this.imageData = imageData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
