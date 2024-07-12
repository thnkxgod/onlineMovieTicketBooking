import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private int duration; // duration in minutes

    public Movie(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Duration: " + duration + " mins";
    }
}
