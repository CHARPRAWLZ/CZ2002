package CZ2002;

import java.util.*;
import java.io.*;

public class AppUI {

    static Scanner sc = new Scanner(System.in);
    public Cineplex getCineplexID;

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
}
