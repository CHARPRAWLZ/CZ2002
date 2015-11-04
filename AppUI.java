package CZ2002;

import java.util.Scanner;

public class AppUI {

    public Cineplex getCineplexID;

    public static void main(String args[]) {
        int choice;
        String username, password;
        Scanner sc = new Scanner(System.in);

        // Welcome page to MOBLIMA
        System.out.println("Welcome to MOBLIMA");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Quit");

        System.out.print("Enter choice: ");
        choice = sc.nextInt();

        do {

            switch (choice) {
                case 1:
                    loginUI();
                    choice = 3;
                    break;
                case 2:
                    signUpUI();
                    choice = 3;
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Error input!");
                    System.out.println("\n1. Login");
                    System.out.println("2. Sign Up");
                    System.out.println("3. Quit");
                    System.out.print("Enter choice: ");
                    choice = sc.nextInt();
            }

        } while (choice != 3);
        
        System.out.println("\n----------------------------------------");
        System.out.println("Thank you for using MOBLIMA");
    }

    private static void loginUI() {

    }

    private static void signUpUI() {

    }
}
