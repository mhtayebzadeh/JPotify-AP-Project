package JPotifyLogic.Entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    int ID = -1;
    private String title;
    private String caption;
    private byte[] imageData;

    public void play() {};
    public Entity()
    {

    }
    public Entity(String title,String caption,byte[] imageData)
    {
        this.title = title;
        this.caption = caption;
        this.imageData = imageData;
    }
    public String getTitle() { return title; }
    public String getCaption() { return caption; }
    public byte[] getImageData() { return imageData; }
    public void setTitle(String title) { this.title = title; }
    public void setCaption(String caption) { this.caption = caption; }
    public void setImageData(byte[] imageData) { this.imageData = imageData; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
