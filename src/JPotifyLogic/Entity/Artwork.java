package JPotifyLogic.Entity;

import java.io.Serializable;
import java.util.Calendar;

//TODO: add Javadoc
public class Artwork extends Entity implements Serializable {
    private long timeStampLastPlayed;

    public Artwork() {
        super();
    }

    public Artwork(String title, String caption, byte[] imageData) {
        super(title, caption, imageData);
    }

    public Artwork(Song song) {
        super(song.getTitle(), song.getCaption(), song.getImageData());
    }

    private long getTimeStampLastPlayed() {
        return timeStampLastPlayed;
    }

    public String getTimeLastPlayed() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.getTimeStampLastPlayed());

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        return hour + ":" + minute;
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }
}
