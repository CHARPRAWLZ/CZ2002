package CZ2002;

import java.util.Scanner;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/*
 author: zach
 */

public class User {

    //variables
    private String id, username, password, name, email, mobileNo;
    private XML userXml;

    private String[] itemName = new String[]{"username", "password", "name", "email", "mobileNo"};

    //constructors
    public User() {
        userXml = new XML("user");
    }

    /**
     *
     * @param username
     * @param password
     * @param name
     * @param email
     * @param mobileNo
     */
    public User(String username, String password, String name, String email, String mobileNo) {
        userXml = new XML("user");
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;

    }

    //accessors
    /**
     * Returns the increment id of the user account
     *
     * @return id
     */
    public String getUserId() {
        return this.id;
    }

    /**
     * Returns the username of the user account
     *
     * @return userName
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the password of the user account
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * returns the name of User instance
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the email address of the User instance
     *
     * @return emailAddress
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * returns the xml of user
     *
     * @return xml of user
     */
    public XML getXml() {
        return this.userXml;
    }

    /**
     * returns the mobile number of the User instance
     *
     * @return mobileNo
     */
    public String getMobileNo() {
        return this.mobileNo;
    }

    //mutators
    /**
     * sets the username of the user account
     *
     * @param user
     */
    public void setUsername(String user) {
        this.username = user;
    }

    /**
     * sets the password of the user account
     *
     * @param pw
     */
    public void setPassword(String pw) {
        this.password = pw;
    }

    /**
     * set the user's name
     *
     * @param myName
     */
    public void setName(String myName) {
        this.name = myName;
    }

    /**
     * set the user's email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set the user's mobile number
     *
     * @param number
     */
    public void setMobileNo(String number) {
        this.mobileNo = number;
    }

    //methods for user
    public boolean checkLogin(String username, String password) {
        //check if username and password exists in xml, if yes, return true, else, false
        int i, j;
        if (!userXml.getFile().exists()) {
            System.out.println("No file found");
        } else if (userXml.getElementCount("username", username) == 0) {
            System.out.println("User not found");
        } else {
            NodeList nList = userXml.getDoc().getElementsByTagName("user");
            for (i = 0; i < nList.getLength(); i++) {
                Element e = userXml.getNodeElement(nList, i);
                NodeList items = e.getElementsByTagName("item");
                String id = e.getAttribute("id");
                for (j = 0; j < items.getLength(); j++) {
                    String itemName = items.item(j).getAttributes().getNamedItem("name").getNodeValue();
                    String itemContent = items.item(j).getTextContent();
                    if (itemName.equals("password")) {
                        if (itemContent.equals(password)) {
                            this.id = id;
                            this.username = userXml.getItemContent(id, "username");
                            this.password = userXml.getItemContent(id, "password");
                            this.name = userXml.getItemContent(id, "name");
                            this.email = userXml.getItemContent(id, "email");
                            this.mobileNo = userXml.getItemContent(id, "mobileNo");
                            return true;
                        } else {
                            System.out.println("Password not match");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void objectToXml() {
        String[] itemContent = new String[]{this.username, this.password, this.name, this.email, this.mobileNo};
        userXml.addItem(itemName, itemContent);
        userXml.writeContent();
        this.id = userXml.getCounter();
    }
    /*
     These methods can be reused later as needed
     */
    /*
     Scanner sc = new Scanner(System.in);
     public Movie movie;
  
     /*public void rateMovie(double rating){
     System.out.println("Please rate the selected movie");
     rating = sc.nextDouble();
     movie.setOverallRating(rating);
     System.out.println("Thank you for rating");
     }
  
     public void reviewMovie(String review){
     System.out.println("Please review the selected movie");
     review = sc.next();
     }
   
     public void bookMovie(){}
     */
}
