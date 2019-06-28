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

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String hourStr = hour / 10 == 0 ? "0" + hour : "" + hour;
        String minuteStr = minute / 10 == 0 ? "0" + minute : "" + minute;

        return hourStr + ":" + minuteStr;
    }

    public void setTimeStampLastPlayed(long timeStampLastPlayed) {
        this.timeStampLastPlayed = timeStampLastPlayed;
    }
}
