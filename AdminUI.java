package CZ2002;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author alfiefarhana
 */
public class AdminUI {

    private static Scanner sc = new Scanner(System.in);
    private static Movie movie;
    private static Cineplex cineplex1, cineplex2, cineplex3;
    private static Cinema cinema;
    private static UserMgr userMgr;
    private static Seat seat;
    private static Showtime showtime;
    private static AppUI appUI;
    private static MovieListing movieMgr;

    private String movieId;
    private XML movieXml;

    public AdminUI() {
        appUI = new AppUI();
        movieXml = new XML("movie");
        movieMgr = new MovieListing();
    }

    public String adminOpt() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======= ADMIN OPTION =======||");
            System.out.println("|| 1: Login as admin          ||");
            System.out.println("|| 2: Login as user           ||");
            System.out.println("|| x: Quit                    ||");
            System.out.println("||============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.homepage();
                    break;
                case "2":
                    appUI.homepage();
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
            System.out.println("||======== ADMIN HOME =========||");
            System.out.println("|| 1: Manage movies            ||");
            System.out.println("|| 2: Manage showtimes         ||");
            System.out.println("|| 3: Manage system settings   ||");
            System.out.println("|| 0: Back to admin option     ||");
            System.out.println("|| x: Quit                     ||");
            System.out.println("||=============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.movieManage();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "0":
                    this.adminOpt();
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
            }
        } while (!opt.matches("x"));
        exitProgram(opt);
        return opt;
    }

    public String movieManage() {
        String[][] itemName = movieMgr.getItemName();
        String opt, id;
        do {
            System.out.println("");
            System.out.println("||========= MANAGE MOVIE =========||");
            System.out.println("|| 1: Create Movie                ||");
            System.out.println("|| 2: Get movie by id             ||");
            System.out.println("|| 3: Update movie by id          ||");
            System.out.println("|| 4: Delete movie by id          ||");
            System.out.println("|| 5: List all movies             ||");
            System.out.println("|| 6: List movies by item content ||");
            System.out.println("|| 0: Back to home                ||");
            System.out.println("|| x: Exit                        ||");
            System.out.println("||================================||");
            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    movieMgr.createMovie();
                    break;
                case "2":
                    movieMgr.getMovie();
                    break;
                case "3":
                    movieMgr.updateMovie();
                    break;
                case "5":
                    movieMgr.listAllMovies();
                    break;
                case "6":
                    movieMgr.listByContent();
                    break;
                /*
                 case "6":
                 System.out.print("Enter movie id: ");
                 id = sc.nextLine();
                 if (xml.displayElement(id) == true) {
                 xml.deleteItem(id);
                 xml.writeContent();
                 System.out.println("- Movie is deleted -");
                 }
                 */
                case "0":
                    this.homepage();
                    break;
                case "x":
                    break;
                default:
                    System.out.println("- Enter a valid input -");
                    break;
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
}
