package CZ2002;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author alfiefarhana
 */
public class AppUI {

    public static Scanner sc = new Scanner(System.in);
    public static Movie movie;
    public static Cineplex cineplex1, cineplex2, cineplex3;
    public static Cinema cinema;
    public static UserMgr userMgr;
    public static Seat seat;
    public static Showtime showtime;
    
    public String movieId;

    public static void main(String args[]) {
        // Start of Cineplex and Cinema initialisation
        ArrayList<Cinema> cinemaList;
        cineplex1 = new Cineplex("CPA", "Cathay Cineplexes", "Woodlands", 3);
        cinemaList = cineplex1.getCinemaList();
        initCinemaType(cinemaList);
        cineplex2 = new Cineplex("CPB", "Cathay Cineplex", "Orchard", 3);
        cinemaList = cineplex2.getCinemaList();
        initCinemaType(cinemaList);
        cineplex3 = new Cineplex("CPC", "The Cathay Cineplex", "CBD", 3);
        cinemaList = cineplex3.getCinemaList();
        initCinemaType(cinemaList);
         // End of Cineplex and Cinema initialisation

        // Welcome page to MOBLIMA
        System.out.println("Welcome to MOBLIMA");
        AppUI ui = new AppUI();
        userMgr = new UserMgr();
        ui.auth();
    }

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

    public String topRanking() {
        String opt;
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
                    System.out.println("- list by ticket sales -");
                    break;
                case "2":
                    System.out.println("- list by overall ratings -");
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

    public String movieList() {
        String opt;
        int j;
        MovieListing movieMgr = new MovieListing();
        String[] list = movieMgr.getMovieList();
        do {
            System.out.println("");
            System.out.println("||======= Movie Listing =======||");
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

    public String movieInfo() {
        String opt;
        MovieListing movieMgr = new MovieListing();
        String[] info = movieMgr.getMovieInfo(this.movieId);
        String[][] itemName = movieMgr.getItemName();
        do {
            System.out.println("");
            System.out.println("||===== Movie Information =====||");
            for (int i = 0; i < info.length; i++) {
                System.out.println("   " + itemName[1][i] + " : " + info[i]);
            }
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
    public String rating() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======= Review Rating ========||");
            System.out.println("|| 1: Create rating             ||");
            System.out.println("|| 0: Back to movie information ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1": break;
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
    public String cineplex() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||========== Cineplex ==========||");
            System.out.println("   1: "+cineplex1.getName());
            System.out.println("   2: "+cineplex1.getName() + " Gold Class");
            System.out.println("   3: "+cineplex2.getName());
            System.out.println("   4: "+cineplex2.getName() + " Gold Class");
            System.out.println("   5: "+cineplex3.getName());
            System.out.println("   6: "+cineplex3.getName() + " Gold Class");
            System.out.println("|| 0: Back to movie information ||");
            System.out.println("|| x: Quit                      ||");
            System.out.println("||==============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
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
    public static void exitProgram(String option) {
        if (option.equals("x")) {
            System.out.println("----------------------------------------");
            System.out.println("Thank you for using MOBLIMA");
            System.exit(0);
        }
    }

    /**
     * Initialise the cinemaType for each of the Cinema in the cinemaList
     *
     * @param cinemaList List of Cinema within a Cineplex
     */
    public static void initCinemaType(ArrayList<Cinema> cinemaList) {
        cinemaList.get(0).setCinemaType("Normal");
        cinemaList.get(1).setCinemaType("Normal");
        cinemaList.get(2).setCinemaType("Gold");
    }
}
