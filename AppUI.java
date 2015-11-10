package CZ2002;

import java.util.*;
import java.io.*;
/**
 * 
 * @author alfiefarhana
 */
public class AppUI {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        // Start of Cineplex and Cinema initialisation
        ArrayList<Cinema> cinemaList;
        Cineplex cineplex1 = new Cineplex("CP1", "Cathay Cineplexes", "Woodlands", 3);
        cinemaList = cineplex1.getCinemaList();
        initCinemaType(cinemaList);
        Cineplex cineplex2 = new Cineplex("CP2", "Cathay Cineplex", "Orchard", 3);
        cinemaList = cineplex2.getCinemaList();
        initCinemaType(cinemaList);
        Cineplex cineplex3 = new Cineplex("CP3", "The Cathay Cineplex", "CBD", 3);
        cinemaList = cineplex3.getCinemaList();
        initCinemaType(cinemaList);
        // End of Cineplex and Cinema initialisation
        String choice;

        // Welcome page to MOBLIMA
        System.out.println("Welcome to MOBLIMA");
        do {
            System.out.println("||===========================||");
            System.out.println("|| 1: Browse as Movie goer   ||");
            System.out.println("|| 2: Login as Admin         ||");
            System.out.println("|| 0: Exit                   ||");
            System.out.println("||===========================||");

            System.out.print("Enter choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    movieGoerUI();
                    choice = "0";
                    break;
                case "2":
                    adminUI();
                    choice = "0";
                    break;
                case "0":
                    break;
                default:
                    System.out.println("\nEnter a valid input!");
            }

        } while (!choice.equals("0"));
        System.out.println("\n----------------------------------------");
        System.out.println("Thank you for using MOBLIMA");
        

    }

    private static void adminUI() {
        System.out.println("\n----------------------------------------");
        System.out.println("Login as Admin");
        String username;
        System.out.print("Username: ");
        username = sc.nextLine();
    }

    private static void movieGoerUI() {
        System.out.println("movieGoerUI");
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
