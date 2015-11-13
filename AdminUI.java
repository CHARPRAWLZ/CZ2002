package CZ2002;

import java.util.*;
import java.io.*;

/**
 *
 * @author alfiefarhana
 */
public class AdminUI {

    private static Scanner sc;
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
        sc = new Scanner(System.in);
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
            /*System.out.println("|| 2: Manage showtimes         ||");*/
            System.out.println("|| 2: Manage system settings   ||");
            System.out.println("|| 0: Back to admin option     ||");
            System.out.println("|| x: Quit                     ||");
            System.out.println("||=============================||");

            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.movieManage();
                    break;
                /*
                 case "2":
                 break;
                 */
                case "2":
                    this.sysSettings();
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
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    movieMgr.getMovie(id);
                    break;
                case "3":
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    movieMgr.updateMovie(id);
                    break;
                case "4":
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    movieMgr.deleteMovie(id);
                    break;
                case "5":
                    movieMgr.listAllMovies();
                    break;
                case "6":
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter item content: ");
                    String content = sc.nextLine();
                    movieMgr.listByContent(name, content);
                    break;
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

    public String sysSettings() {
        String opt;
        do {
            System.out.println("");
            System.out.println("||======== SYSTEM SETTINGS =======||");
            System.out.println("|| 1: Manage holidays             ||");
            System.out.println("|| 2: Manage ticket prices        ||");
            System.out.println("|| 0: Back to home                ||");
            System.out.println("|| x: Exit                        ||");
            System.out.println("||================================||");
            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    this.holidayMgr();
                    break;
                case "2":
                    break;
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

    public String holidayMgr() {
        String opt;
        String[][] itemName = new String[][]{{"name", "date"}, {"Name", "Date"}};
        XML holXml = new XML("holiday");
        do {
            System.out.println("");
            System.out.println("||======== MANAGE HOLIDAYS =======||");
            System.out.println("|| 1: View holidays               ||");
            System.out.println("|| 2: Add holiday                 ||");
            System.out.println("|| 3: Update holiday              ||");
            System.out.println("|| 4: Delete holiday              ||");
            System.out.println("|| 0: Back to system settings     ||");
            System.out.println("|| x: Exit                        ||");
            System.out.println("||================================||");
            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":

                    String[] ids = holXml.getElement();
                    for (int i = 0; i < ids.length; i++) {
                        System.out.println("\n------------------");
                        System.out.println("HOLIDAY ID : " + ids[i]);
                        String[] info = new String[itemName[0].length];
                        for (int j = 0; j < itemName[0].length; j++) {
                            info[j] = holXml.getItemContent(ids[i], itemName[0][j]);
                        }
                        for (int j = 0; j < info.length; j++) {
                            System.out.println("   " + itemName[0][j] + " : " + info[j]);
                        }
                    }
                    break;
                case "2":
                    String[] itemContent = new String[itemName[0].length];
                    for (int i = 0; i < itemName[0].length; i++) {
                        System.out.print("Enter " + itemName[1][i] + " : ");
                        itemContent[i] = sc.nextLine();
                    }
                    holXml.addItem(itemName[0], itemContent);
                    holXml.writeContent();
                    System.out.println("- Holiday added into database -");
                    break;
                case "3":
                    System.out.print("Enter holiday id: ");
                    String id = sc.nextLine();
                    if (holXml.checkIdExists(id)) {
                        System.out.print("Enter item name: ");
                        String name = sc.nextLine();
                        if (holXml.checkItemExists(id, name)) {
                            System.out.print("Enter new value: ");
                            String val = sc.nextLine();
                            System.out.println(name);
                            System.out.println(val);
                            holXml.editItem(id, name, val);
                            holXml.writeContent();
                            System.out.println("- Holiday has been updated -");
                        }
                    }
                    break;
                 case "4":
                    System.out.print("Enter holiday id: ");
                    id = sc.nextLine();
                    if (holXml.checkIdExists(id)) {
                        holXml.deleteItem(id);
                        holXml.writeContent();
                        System.out.println("- Holiday is deleted -");
                    }
                 break;
                case "0":
                    this.sysSettings();
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
