package CZ2002;

import java.util.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author alfiefarhana
 */
public class UserMgr {

    public static Scanner sc = new Scanner(System.in);
    private final String[] itemName;
    User user;
    private final XML userXml;

    public UserMgr() {
        this.itemName = new String[]{"username", "password", "name", "email", "mobileNo"};
        user = new User();
        userXml = new XML("user");
    }

    public boolean login() {
        String username, password;
        System.out.print("Username : ");
        username = sc.nextLine();
        System.out.print("Password : ");
        password = sc.nextLine();
        if (this.checkLogin(username, password)) {
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

    public boolean isLoggedIn() {
        if (user.getUserId() != null) {
            return true;
        }
        return false;
    }

    //methods for user
    public boolean checkLogin(String username, String password) {
        //check if username and password exists in xml, if yes, return true, else, false
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

    public void objectToXml() {
        String[] itemContent = new String[]{user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getMobileNo()};
        userXml.addItem(itemName, itemContent);
        userXml.writeContent();
        user.setUserId(userXml.getCounter());
    }

    /**
     * returns the xml of user
     *
     * @return xml of user
     */
    public XML getXml() {
        return this.userXml;
    }
}
