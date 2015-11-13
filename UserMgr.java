package CZ2002;

import java.util.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class manages the users in the database. 
 * It controls the creation of user accounts, logging in to the account
 * as well as checking if the account has already been logged in to prevent
 * multiple login so as not to compromise security
 * @author alfiefarhana
 */
public class UserMgr {

    public static Scanner sc = new Scanner(System.in);
    private final String[] itemName;
    User user;
    private final XML userXml, employeeXml;
    AdminUI adminUI;
 
    /**
     * Creates a new UserManager with the attributes username, password, name, email and mobileNo
     * It creates a new user and stores it into the database
     */
    public UserMgr() {
        this.itemName = new String[]{"username", "password", "name", "email", "mobileNo"};
        user = new User();
        adminUI = new AdminUI();
        userXml = new XML("user");
        employeeXml = new XML("employee");
    }
    /**
     * Prompts for a username and password then checks the database entry containing the user's account
     * ensures that the password is the correct one to grant access
     * @return false unless login credentials are entered correctly
     */
    public boolean login() {
        String username, password;
        System.out.print("Username : ");
        username = sc.nextLine();
        System.out.print("Password : ");
        password = sc.nextLine();
        if (this.checkLogin(username, password)) {
            System.out.println("\n----------------");
            System.out.print("Username : " + user.getUsername());
            if (this.isAdmin()) System.out.println(" (ADMIN)");
            else System.out.println("");
            System.out.println("Name : " + user.getName());
            System.out.println("Email : " + user.getEmail());
            System.out.println("Mobile Number : " + user.getMobileNo());
            if (this.isAdmin()) adminUI.adminOpt();
            return true;
        }
        return false;
    }
    /**
     * Creates a new user account and stores it in the database
     * Prompts for desired username, if it doesn't already exist in the database, use it.
     * Next, prompt the desired password and prompts for the entered password to confirm
     * After login credentials entered, prompts for the user's details
     * Then saves everything into the database
     * @return 
     */
    public boolean signUp() {
        int userCount = 0;
        String username;
        do {
            System.out.print("Enter username : ");
            username = sc.nextLine();
            userCount = this.getXml().getElementCount("username", username);
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
                System.out.println("- Password did not match. Please enter password again. -");
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
        this.objectToXml();
        System.out.println("- You have successfully signed up for MOBLIMA! -");
        return true;
    }
    /**
     * Checks if the user is already logged in
     * @return false unless a user object with the same userId already exists 
     */
    public boolean isLoggedIn() {
        if (user.getUserId() != null) {
            return true;
        }
        return false;
    }
    public boolean isAdmin() {
        if(employeeXml.getElementCount("username", this.user.getUsername())==0) return false;
        return true;
    }

    //methods for user
    /**
     * check if username and password exists in the database
     * @param username
     * @param password
     * @return false unless both username and it's corresponding password exists in the database
     */
    public boolean checkLogin(String username, String password) {
        int i, j;
        if (userXml.getElementCount("username", username) == 0) {
            System.out.println("- User not found -");
        } else {
            NodeList nList = userXml.getDoc().getElementsByTagName("user");
            for (i = 0; i < nList.getLength(); i++) {
                Element e = userXml.getNodeElement(nList, i);
                String id = e.getAttribute("id");
                String uname = userXml.getItemContent(id, "username");
                String pw = userXml.getItemContent(id, "password");
                if (username.equals(uname) && password.equals(pw)) {
                    user.setUserId(id);
                    user.setUsername(userXml.getItemContent(id, "username"));
                    user.setPassword(userXml.getItemContent(id, "password"));
                    user.setName(userXml.getItemContent(id, "name"));
                    user.setEmail(userXml.getItemContent(id, "email"));
                    user.setMobileNo(userXml.getItemContent(id, "mobileNo"));
                    return true;
                }
            }

            System.out.println("- Password not match -");
        }
        return false;
    }
    /**
     * adds the user object to the database
     */
    private void objectToXml() {
        String[] itemContent = new String[]{user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getMobileNo()};
        userXml.addItem(itemName, itemContent);
        userXml.writeContent();
        user.setUserId(userXml.getCounter());
    }

    /**
     * returns the database entry of user
     *
     * @return xml of user
     */
    public XML getXml() {
        return this.userXml;
    }
}
