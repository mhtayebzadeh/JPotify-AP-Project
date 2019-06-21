package JPotifyLogic.Entity;

public abstract class Entity {
    private String title;
    private String caption;
    private byte[] imageData;

    public void play() {};

    public String getTitle() { return title; }
    public String getCaption() { return caption; }
    public byte[] getImageData() { return imageData; }
    public void setTitle(String title) { this.title = title; }
    public void setCaption(String caption) { this.caption = caption; }
    public void setImageData(byte[] imageData) { this.imageData = imageData; }
}
