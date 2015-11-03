package CZ2002;

import java.util.Scanner;
public class AppUI {
   public Cineplex getCineplexID;
   public static void main(String args[]){
   int choice;
   System.out.println("1.Login as admin");
   System.out.println("2.Continue as user");
   System.out.println("3: Quit");
   Scanner sc = new Scanner(System.in);
   choice = sc.nextInt();
   switch(choice){
       case 1:
           //go to CinemaStaff UI
           break;
       case 2:
           //go to MovieBooking UI
           break;
       case 3:
           return;
   }while (choice<3)
       choice = sc.nextInt();
      }
}
