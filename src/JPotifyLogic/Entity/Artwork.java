package JPotifyLogic.Entity;

import java.io.Serializable;

public class Artwork extends Entity implements Serializable {
    private long timeStampLastPlayed;
    public Artwork()
    {
        super();
    }
    public Artwork(String title, String caption, byte[] imageData){
        super(title,caption, imageData);
    }
    public Artwork(Song song)
    {
        super(song.getTitle(),song.getCaption(),song.getImageData());
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }

    public long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }
}
