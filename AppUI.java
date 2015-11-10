package CZ2002;

import java.util.*;
import java.io.*;

public class AppUI {

    static Scanner sc = new Scanner(System.in);
    ArrayList<Cinema> cinemaList;
    // Start of Cineplex and Cinema initialisation
    Cineplex cineplex1 = new Cineplex("CP1", "Cathay Cineplexes", "Woodlands", 3);
    cinemaList = cineplex1.getCinemaList();
    initCinemaType(cinemaList);
    cineplex1.setCinemaList();
    Cineplex cineplex2 = new Cineplex("CP2", "Cathay Cineplex", "Orchard" , 3);
    cinemaList = cineplex2.getCinemaList();
    initCinemaType(cinemaList);
    cineplex2.setCinemaList();
    Cineplex cineplex3 = new Cineplex("CP3", "The Cathay Cineplex", "CBD", 3);
    cinemaList = cineplex3.getCinemaList();
    initCinemaType(cinemaList);
    cineplex3.setCinemaList();
    // End of Cineplex and Cinema initialisation

    public static void main(String args[]) {
        String choice;

        // Welcome page to MOBLIMA
        System.out.println("Welcome to MOBLIMA");
        do {
            System.out.println("1. Login as Admin");
            System.out.println("2. Browse as Movie goer");
            System.out.println("3. Quit");

            System.out.print("Enter choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    adminUI();
                    choice = "3";
                    break;
                case "2":
                    movieGoerUI();
                    choice = "3";
                    break;
                case "3":
                    break;
                default:
                    System.out.println("\nEnter a valid input!");
            }

        } while (!choice.equals("3"));
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
     * @param cinemaList List of Cinema within a Cineplex
     */
    public static void initCinemaType(ArrayList<Cinema> cinemaList) {
        cinemaList.get(0).setCinemaType("Normal");
        cinemaList.get(1).setCinemaType("Normal");
        cinemaList.get(2).setCinemaType("Gold");
    }
}
