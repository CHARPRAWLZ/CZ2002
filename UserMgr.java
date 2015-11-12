package CZ2002;

import java.util.*;
import java.io.*;

/**
 *
 * @author alfiefarhana
 */
public class UserMgr {

    public static Scanner sc = new Scanner(System.in);
    User user;

    public UserMgr() {
        user = new User();
    }

    public boolean login() {
        String username, password;
        System.out.print("Username : ");
        username = sc.nextLine();
        System.out.print("Password : ");
        password = sc.nextLine();
        if (user.checkLogin(username, password)) {
            System.out.println("\n----------------");
            System.out.println("Username : " + user.getUsername());
            System.out.println("Name : " + user.getName());
            System.out.println("Email : " + user.getEmail());
            System.out.println("Mobile Number : " + user.getMobileNo());
            return true;
        }
        return false;
    }

    public boolean signUp() {
        int userCount = 0;
        String username;
        do {
            System.out.print("Enter username : ");
            username = sc.nextLine();
            userCount = user.getXml().getElementCount("username", username);
            if (userCount > 0) {
                System.out.println("Username already exists. Please use a different username.");
                System.out.println("");
            }
        } while (userCount > 0);
        String password, password2;
        do {
            System.out.print("Enter password : ");
            password = sc.nextLine();
            System.out.print("Re-enter password : ");
            password2 = sc.nextLine();
            if (!password.equals(password2)) {
                System.out.println("Password did not match. Please enter password again.");
                System.out.println("");
            }
        } while (!password.equals(password2));
        System.out.print("Enter full name : ");
        String name = sc.nextLine();

        System.out.print("Enter email : ");
        String email = sc.nextLine();

        System.out.print("Enter Mobile Number : ");
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
        return true;
    }
    public boolean isLoggedIn() {
        if(user.getUserId()!=null) return true;
        return false;
    }

}
