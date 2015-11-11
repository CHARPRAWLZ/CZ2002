package CZ2002;

import java.util.*;
import java.io.*;

/**
 *
 * @author alfiefarhana
 */
public class AppUI {

    public static Scanner sc = new Scanner(System.in);
    public static Movie movie;
    public static Cineplex cineplex;
    public static Cinema cinema;
    public static User user;
    public static Seat seat;
    public static Showtime showtime;

    public static void main(String args[]) {/*
         // Start of Cineplex and Cinema initialisation
         ArrayList<Cinema> cinemaList;
         Cineplex cineplex1 = new Cineplex("CPA", "Cathay Cineplexes", "Woodlands", 3);
         cinemaList = cineplex1.getCinemaList();
         initCinemaType(cinemaList);
         Cineplex cineplex2 = new Cineplex("CPB", "Cathay Cineplex", "Orchard", 3);
         cinemaList = cineplex2.getCinemaList();
         initCinemaType(cinemaList);
         Cineplex cineplex3 = new Cineplex("CPC", "The Cathay Cineplex", "CBD", 3);
         cinemaList = cineplex3.getCinemaList();
         initCinemaType(cinemaList);
         // End of Cineplex and Cinema initialisation
         */

        String choice;

        // Welcome page to MOBLIMA
        System.out.println("Welcome to MOBLIMA");
        do {
            System.out.println("||======================||");
            System.out.println("|| 1: Login             ||");
            System.out.println("|| 2: Continue as guest ||");
            System.out.println("|| 0: Exit              ||");
            System.out.println("||======================||");

            System.out.print("Enter choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    guest();
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

    public static void login() {
        String username, password;
        System.out.println("\n[ Enter / to go back ]");
        System.out.print("Username: ");
        username = sc.nextLine();
        if (!username.equals("/")) {
            System.out.print("Password: ");
            password = sc.nextLine();
            
        }
    }

    public static void guest() {
        System.out.println("Continue as Guest");
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
