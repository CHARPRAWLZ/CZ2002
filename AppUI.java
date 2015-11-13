package CZ2002;

import java.util.*;
import java.io.*;

/**
 * This is the main screen when you start the application This is also the main
 * class
 *
 * @author alfiefarhana
 */
public class AppUI {

    public static Scanner sc = new Scanner(System.in);
    public static Movie movie;
    public static CinemaMgr cinemaMgr;
    public static Cinema cinema;
    public static UserMgr userMgr;
    public static Seat seat;
    public static Showtime showtime;
    public static ReviewRatingList reviewMgr;

    public String movieId;

    public static void main(String args[]) {
        cinemaMgr = new CinemaMgr();
        userMgr = new UserMgr();

        System.out.println("Welcome to MOBLIMA");
        AppUI ui = new AppUI();
        ui.auth();
    }

    /**
     * Choose to login to book movie or continue as guest to view information
     *
     * @return choice
     */
    public String auth() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||=== AUTHENTICATION ===||");
            System.out.println("|| 1: Login             ||");
            System.out.println("|| 2: Sign up           ||");
            System.out.println("|| 3: Continue as guest ||");
            System.out.println("|| x: Quit              ||");
            System.out.println("||======================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    if (userMgr.login()) {
                        this.homepage();
                    }
                    break;
                case "2":
                    if (userMgr.signUp()) {
                        this.homepage();
                    }
                    break;
                case "3":
                    this.homepage();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * The home screen when you logged in Allow you to list movies and top 5
     * movie rating
     *
     * @return option
     */
    public String homepage() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||============ HOME ===========||");
            System.out.println("|| 1: List Movies              ||");
            System.out.println("|| 2: List top 5 movie ranking ||");
            if (userMgr.isLoggedIn()) {
                System.out.println("|| 3: View booking history     ||");
            } else {
                System.out.println("|| 0: Back to authentication   ||");
            }
            System.out.println("|| x: Quit                     ||");
            System.out.println("||=============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.movieList();
                    break;
                case "2":
                    this.topRanking();
                    break;
                case "3":
                    if (userMgr.isLoggedIn()) {
                        System.out.println("- booking history listed -");
                    }
                    break;
                case "0":
                    this.auth();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * This screen shows the top 5 movies
     *
     * @return
     */
    public String topRanking() {
        String opt;
        RankingMgr ranking = new RankingMgr();
        String[][] topFiveRanking;
        do {
            System.out.println("");
            System.out.println("||====== LIST TOP 5 MOVIE RANKING =======||");
            System.out.println("|| 1: List by ticket sales               ||");
            System.out.println("|| 2: List by overall reviewer's ratings ||");
            System.out.println("|| 0: Back to home                       ||");
            System.out.println("|| x: Quit                               ||");
            System.out.println("||=======================================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    topFiveRanking = ranking.rankingTicketSales();
                    System.out.println("- list by ticket sales -");
                    for (int i = 0; i < 5; i++) {
                        System.out.println("1) " + topFiveRanking[i][0] + " - " + topFiveRanking[i][1]);
                    }
                    break;
                case "2":
                    topFiveRanking = ranking.rankingReviewsRating();
                    System.out.println("- list by overall ratings -");
                    for (int i = 0; i < 5; i++) {
                        System.out.println("1) " + topFiveRanking[i][0] + " - " + topFiveRanking[i][1]);
                    }
                    break;
                case "0":
                    this.homepage();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * Lists all the movies
     *
     * @return
     */
    public String movieList() {
        String opt;
        int j;
        MovieListing movieMgr = new MovieListing();
        String[] list = movieMgr.getMovieList();
        do {
            System.out.println("");
            System.out.println("||======= MOVIE LISTING =======||");
            for (int i = 0; i < list.length; i++) {
                System.out.println("   " + (i + 1) + ": " + list[i]);
            }
            System.out.println("|| 0: Back to home             ||");
            System.out.println("|| x: Quit                     ||");
            System.out.println("||=============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            String[] lists = movieMgr.idList();
            for (j = 1; j <= lists.length; j++) {
                if (opt.equals(Integer.toString(j))) {
                    this.movieId = lists[j - 1];
                    this.movieInfo();
                }
            }
            if (j == lists.length + 1) {
                switch (opt) {
                    case "0":
                        this.homepage();
                        break;
                    case "x":
                        break;
                    default:
                        System.out.println("- Enter a valid input -");
                }
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * displays information about the movie
     *
     * @return
     */
    public String movieInfo() {
        String opt;
        MovieListing movieMgr = new MovieListing();
        reviewMgr = new ReviewRatingList(this.movieId);
        String[] info = movieMgr.getMovieInfo(this.movieId);
        String[][] itemName = movieMgr.getItemName();
        do {
            System.out.println("");
            System.out.println("||===== MOVIE INFORMATION =====||");
            for (int i = 0; i < info.length; i++) {
                System.out.println("   " + itemName[1][i] + " : " + info[i]);
            }
            System.out.format("   Overall Rating: %.2f\n",reviewMgr.getOverallRating());
            System.out.println("||                             ||");
            System.out.println("|| 1: View review rating       ||");
            System.out.println("|| 2: View Showtimes           ||");
            System.out.println("|| 0: Back to movie listing    ||");
            System.out.println("|| x: Quit                     ||");
            System.out.println("||=============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.rating();
                    break;
                case "2":
                    this.cineplex();
                    break;
                case "0":
                    this.movieList();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * manages the rating of a movie
     *
     * @return
     */
    public String rating() {
        String opt, rating, review;
        MovieListing movieMgr = new MovieListing();
        do {
            System.out.println("");
            System.out.println("||======= REVIEW RATING ========||");
                System.out.println("   Title : " + movieMgr.getMovieInfo(this.movieId)[0]);
            reviewMgr = new ReviewRatingList(this.movieId);
            ArrayList<ReviewRating> reviewListArr = reviewMgr.getListArr();
            for (ReviewRating rr : reviewListArr) {
                System.out.println("   ---------------");
                System.out.println("   review  : " + rr.getReview());
                System.out.println("   rating  : " + rr.getRating());
            }

            System.out.println("||                              ||");
            if (userMgr.isLoggedIn()) {
                System.out.println("|| 1: Create rating             ||");
            }
            System.out.println("|| 0: Back to movie information ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    if (userMgr.isLoggedIn()) {
                        System.out.print("Rating (1-5): ");
                        rating = sc.nextLine();
                        System.out.print("Review: ");
                        review = sc.nextLine();
                        reviewMgr.addReview(review, Integer.parseInt(rating));
                    }
                    break;
                case "0":
                    this.movieInfo();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     *
     * @return
     */
    public String cineplex() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||========== CINEPLEX ==========||");
            System.out.println("   1: " + cinemaMgr.getCineplex(0).getName());
            System.out.println("   2: " + cinemaMgr.getCineplex(0).getName() + " Gold Class");
            System.out.println("   3: " + cinemaMgr.getCineplex(1).getName());
            System.out.println("   4: " + cinemaMgr.getCineplex(1).getName() + " Gold Class");
            System.out.println("   5: " + cinemaMgr.getCineplex(2).getName());
            System.out.println("   6: " + cinemaMgr.getCineplex(2).getName() + " Gold Class");
            System.out.println("|| 0: Back to movie information ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            for (int j = 1; j <= 6; j++) {
                if (opt.equals(Integer.toString(j))) {
                    this.dateListing();
                }
            }
            switch (opt) {
                case "0":
                    this.movieInfo();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * display the dates available for movie
     *
     * @return
     */
    public String dateListing() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======== DATE LISTING ========||");
            System.out.println("   1: 24 Dec 2015                 ");
            System.out.println("   2: 25 Dec 2015                 ");
            System.out.println("|| 0: Back to cineplex          ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            for (int j = 1; j <= 2; j++) {
                if (opt.equals(Integer.toString(j))) {
                    this.dateListing();
                }
            }
            switch (opt) {
                case "1":
                    this.showtime();
                    break;
                case "0":
                    this.cineplex();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * display the timings available
     *
     * @return
     */
    public String showtime() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||========== SHOWTIME ==========||");
            System.out.println("   1: 12:00pm                     ");
            System.out.println("   2: 05:00pm                     ");
            System.out.println("|| 0: Back to date listing      ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            for (int j = 1; j <= 2; j++) {
                if (opt.equals(Integer.toString(j))) {
                    this.dateListing();
                }
            }
            switch (opt) {
                case "1":
                    this.viewLayout();
                    break;
                case "0":
                    this.dateListing();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * view the layout of the cinema
     *
     * @return
     */
    public String viewLayout() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======== VIEW LAYOUT =========||");
            System.out.println("|| 1: Select seat               ||");
            System.out.println("|| 0: Back to showtime          ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.selectSeat();
                    break;
                case "0":
                    this.showtime();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * choose the seat to book
     *
     * @return
     */
    public String selectSeat() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======== SELECT SEAT =========||");
            System.out.println("|| 1: To Booking                ||");
            System.out.println("|| 0: Back to view layout       ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.book();
                    break;
                case "0":
                    this.viewLayout();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * books the selected seat
     *
     * @return
     */
    public String book() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======== MAKE BOOKING ========||");
            System.out.println("|| 1: To booking history        ||");
            System.out.println("|| 0: Back to select seat       ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.bookingHistory();
                    break;
                case "0":
                    this.selectSeat();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * displays booking history
     *
     * @return
     */
    public String bookingHistory() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||====== BOOKING HISTORY =======||");
            System.out.println("|| 0: Back to home              ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "0":
                    this.homepage();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    /**
     * exits the whole application when the "x" is clicked
     *
     * @param option
     */
    public static void exitProgram(String option) {
        if (option.equals("x")) {
            System.out.println("----------------------------------------");
            System.out.println("Thank you for using MOBLIMA");
            System.exit(0);
        }
    }
}
