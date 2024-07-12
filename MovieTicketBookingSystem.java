import java.io.*;
import java.util.*;

public class MovieTicketBookingSystem {
    private static final String MOVIES_FILE = "movies.txt";
    private static final String BOOKINGS_FILE = "bookings.txt";
    private static List<Movie> movies = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadMovies();
        loadBookings();
        if (movies.isEmpty()) {
            initializeMovies();
        }
        int choice;
        do {
            System.out.println("1. View Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Add Movie");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    viewMovies();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    addMovie();
                    break;
                case 5:
                    saveMovies();
                    saveBookings();
                    System.out.println("Thank you for using the Movie Ticket Booking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void loadMovies() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MOVIES_FILE))) {
            movies = (List<Movie>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Movies file not found. Starting with an empty movie list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveMovies() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MOVIES_FILE))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadBookings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKINGS_FILE))) {
            bookings = (List<Booking>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Bookings file not found. Starting with an empty bookings list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveBookings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKINGS_FILE))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void viewMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }
        }
    }

    private static void bookTicket() {
        if (movies.isEmpty()) {
            System.out.println("No movies available for booking.");
            return;
        }
        viewMovies();
        System.out.print("Select movie number to book: ");
        int movieNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (movieNumber < 1 || movieNumber > movies.size()) {
            System.out.println("Invalid movie number.");
            return;
        }

        Movie movie = movies.get(movieNumber - 1);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Booking booking = new Booking(name, movie);
        bookings.add(booking);
        System.out.println("Ticket booked successfully!");
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private static void addMovie() {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter movie genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter movie duration (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Movie movie = new Movie(title, genre, duration);
        movies.add(movie);
        System.out.println("Movie added successfully!");
    }

    private static void initializeMovies() {
        movies.add(new Movie("Inception", "Sci-Fi", 148));
        movies.add(new Movie("The Dark Knight", "Action", 152));
        movies.add(new Movie("Interstellar", "Sci-Fi", 169));
        saveMovies();
    }
}
