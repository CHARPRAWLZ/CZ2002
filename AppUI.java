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
        user = new User();

        // Welcome page to MOBLIMA
        System.out.println("Welcome to MOBLIMA");
        do {
            System.out.println("||======================||");
            System.out.println("|| 1: Login             ||");
            System.out.println("|| 2: Sign up           ||");
            System.out.println("|| 3: Continue as guest ||");
            System.out.println("|| 0: Exit              ||");
            System.out.println("||======================||");

            System.out.print("Enter choice: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    signUp();
                    break;
                case "3":
                    landing();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("\nEnter a valid input!");
            }
            System.out.println("");
        } while (!choice.equals("0"));
        System.out.println("----------------------------------------");
        System.out.println("Thank you for using MOBLIMA");

    }

    public static void login() {
        String username, password;
        System.out.println("\n[ Enter / to go back ]");
        System.out.print("Username : ");
        username = sc.nextLine();
        if (!username.equals("/")) {
            System.out.print("Password : ");
            password = sc.nextLine();
            if (user.checkLogin(username, password)) {
                System.out.println("\n----------------");
                System.out.println("Username : " + user.getUsername());
                System.out.println("Name : " + user.getName());
                System.out.println("Email : " + user.getEmail());
                System.out.println("Mobile Number : " + user.getMobileNo());
            }
        }
    }

    public static void signUp() {
        int userCount = 0;
        String username;
        do {
            System.out.println("");
            System.out.print("Enter username : ");
            username = sc.nextLine();
            userCount = user.getXml().getElementCount("username", username);
            if (userCount > 0) {
                System.out.println("Username already exists. Please use a different username.");
            }
        } while (userCount > 0);
        String password, password2;
        do {
            System.out.println("");
            System.out.print("Enter password : ");
            password = sc.nextLine();
            System.out.print("Re-enter password : ");
            password2 = sc.nextLine();
            if (!password.equals(password2)) {
                System.out.println("Password did not match. Please enter password again.");
            }
        } while (!password.equals(password2));
        System.out.print("\nEnter full name : ");
        String name = sc.nextLine();

        System.out.print("\nEnter email : ");
        String email = sc.nextLine();

        System.out.print("\nEnter Mobile Number : ");
        String mobileNo = sc.nextLine();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setMobileNo(mobileNo);
        
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getMobileNo());
        user.objectToXml();
        System.out.println(user.getUserId());
    }

    public static void landing() {
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
