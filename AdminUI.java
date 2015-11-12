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

    private String movieId;
    private XML movieXml;

    public AdminUI() {
        movieXml = new XML("movie");
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
        String[][] itemName = new String[][]{{"title", "synopsis", "director", "cast", "movieType", "movieStatus", "overallRating"},
                {"Title","Synopsis","Director","Cast","Movie Type","Movie Status","Overall R"}};
        String opt;
        do {
            System.out.println("||=============================||");
            System.out.println("|| 1: Create Movie             ||");
            System.out.println("|| 2: List all movies          ||");
            System.out.println("|| 3: List movies by item      ||");
            System.out.println("|| 4: Read movie by movie id   ||");
            System.out.println("|| 5: Update movie by movie id ||");
            System.out.println("|| 6: Delete movie by movie id ||");
            System.out.println("|| 7: Element count            ||");
            System.out.println("|| 0: Exit                     ||");
            System.out.println("||=============================||");
            System.out.print("Enter choice: ");
            opt = sc.nextLine();
            switch (opt) {
                case "1":
                    String[] itemContent = new String[itemName.length];
                    for (int i = 0; i < itemName.length; i++) {
                        System.out.print("Enter " + itemName[i] + " : ");
                        itemContent[i] = sc.nextLine();
                    }
                    xml.addItem(itemName, itemContent);
                    xml.writeContent();
                    System.out.println("- Movie added into database -");

                    break;
                case "2":
                    String[] arrId;
                    arrId = xml.getElement();
                    for (int i = 0; i < arrId.length; i++) {
                        System.out.println("id : " + arrId[i]);
                        for (int j = 0; j < itemName.length; j++) {
                            System.out.println(itemName[j] + " : " + xml.getItemContent(arrId[i], itemName[j]));
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
                    xml.displayElement(name2, content2);
                    break;
                case "4":
                    System.out.print("Enter movie id: ");
                    id = sc.nextLine();
                    xml.displayElement(id);
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
                default:
                    System.out.println("- Enter a valid input -");
                    break;
            }
            while (!opt.matches("x"));
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
