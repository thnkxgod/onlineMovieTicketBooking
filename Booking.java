import java.io.Serializable;

public class Booking implements Serializable {
    private String userName;
    private Movie movie;

    public Booking(String userName, Movie movie) {
        this.userName = userName;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "User: " + userName + ", Movie: " + movie;
    }
}
