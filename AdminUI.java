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
    private static MovieListing movieListing;

    private String movieId;
    private XML movieXml;

    public AdminUI() {
        appUI = new AppUI();
        movieXml = new XML("movie");
        movieListing = new MovieListing();
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
            System.out.println("|| x: Quit                     ||");
            System.out.println("||=============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.movieMgr();
                    break;
                case "2":
                    break;
                case "3":
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

    public String movieMgr() {
        String[][] itemName = movieListing.getItemName();
        String opt, id;
        do {
            System.out.println("||=============================||");
            System.out.println("|| 1: Create Movie             ||");
            System.out.println("|| 2: List all movies          ||");
            System.out.println("|| 3: List movies by item      ||");
            System.out.println("|| 4: Read movie by movie id   ||");
            System.out.println("|| 5: Update movie by movie id ||");
            System.out.println("|| 6: Delete movie by movie id ||");
            System.out.println("|| 7: Element count            ||");
            System.out.println("|| x: Exit                     ||");
            System.out.println("||=============================||");
            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1": movieListing.createMovie();
                    break;
                 case "2":
                     String[] movieIds = movieListing.getMovieList();
                     for(int i=0;i<movieIds.length;i++){
                         System.out.println(itemName[1][i]+" : "+movieIds[i]);
                     }
                      break;
                     /*
                 String[] arrId;
                 arrId = movieXml.getElement();
                 for (int i = 0; i < arrId.length; i++) {
                 System.out.println("id : " + arrId[i]);
                 for (int j = 0; j < itemName.length; j++) {
                 System.out.println(itemName[1][j] + " : " + movieXml.getItemContent(arrId[i], itemName[1][j]));
                 }
                 }
                 if (arrId.length == 0) {
                 System.out.println("No element found");
                 }
                 break;
                 case "3":
                 System.out.print("Enter item name: ");
                 String name2 = sc.nextLine();
                 System.out.print("Enter item content: ");
                 String content2 = sc.nextLine();
                 movieXml.displayElement(name2, content2);
                 break;
                 case "4":
                 System.out.print("Enter movie id: ");
                 id = sc.nextLine();
                 movieXml.displayElement(id);
                 break;
                 case "5":
                 System.out.print("Enter movie id: ");
                 id = sc.nextLine();
                 if (xml.displayElement(id) == true) {
                 System.out.print("Enter item name: ");
                 String name = sc.nextLine();
                 if (xml.checkItemExists(id, name) == true) {
                 System.out.print("Enter new value: ");
                 String val = sc.nextLine();
                 xml.editItem(id, name, val);
                 xml.displayElement(id);
                 xml.writeContent();
                 System.out.println("- Movie has been updated -");
                 }
                 }
                 break;
                 case "6":
                 System.out.print("Enter movie id: ");
                 id = sc.nextLine();
                 if (xml.displayElement(id) == true) {
                 xml.deleteItem(id);
                 xml.writeContent();
                 System.out.println("- Movie is deleted -");
                 }
                 break;
                 case "7":
                 System.out.print("Enter item name: ");
                 String name3 = sc.nextLine();
                 System.out.print("Enter item content: ");
                 String content3 = sc.nextLine();
                 System.out.println(xml.getElementCount(name3, content3));
                 */
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
