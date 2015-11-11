package CZ2002;

import java.util.Scanner;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/*
 author: zach
 */

public class User {

    //variables

    private String userId, name, emailAddress, mobileNo, userName, password;
    XML userXml;
    private int age;

    //constructors

    /**
     * Constructs an instance of class User.
     *
     * @param user
     * @param pw
     * @param id
     * @param name
     * @param email
     * @param number
     * @param age
     */
    public User() {
        userXml = new XML("user");
    }

    public User(String user, String pw, String id, String name, String email, String number, int age) {
        this.userName = user;
        this.password = pw;
        this.userId = id;
        this.name = name;
        this.emailAddress = email;
        this.mobileNo = number;
        this.age = age;

    }

    //accessors

    /**
     * Returns the username of the user account
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the password of the user account
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }
    //mutators

    /**
     * returns the UserID of the User instance.
     *
     * @return userId
     */
    public String getUserID() {
        return userId;
    }

    /**
     * returns the name of User instance
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the email address of the User instance
     *
     * @return emailAddress
     */
    public String getEmail() {
        return emailAddress;
    }

    /**
     * returns the mobile number of the User instance
     *
     * @return mobileNo
     */
    public String getMobileNumber() {
        return mobileNo;
    }

    /**
     * returns the age of the user
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    //mutators

    /**
     * sets the username of the user account
     *
     * @param user
     */
    public void setUserName(String user) {
        userName = user;
    }

    /**
     * sets the password of the user account
     *
     * @param pw
     */
    public void setPassword(String pw) {
        password = pw;
    }

    /**
     * set the user's name
     *
     * @param uname
     */
    public void setName(String myName) {
        name = myName;
    }

    /**
     * set the user's email
     *
     * @param email
     */
    public void setEmail(String email) {
        emailAddress = email;
    }

    /**
     * set the user's mobile number
     *
     * @param number
     */
    public void setMobileNumber(String number) {
        mobileNo = number;
    }

    /**
     * sets the user's age in terms of years
     *
     * @param years
     */
    public void setAge(int years) {
        age = years;
    }

    //methods for user

    public boolean checkLogin(String username, String password) {
        //check if username and password exists in xml, if yes, return true, else, false
        int i,j;
        if (!userXml.getFile().exists()) {
            System.out.println("No file found");
        } else {
            NodeList nList = userXml.getDoc().getElementsByTagName("user");
            for (i = 0; i < nList.getLength(); i++) {
                Element e = userXml.getNodeElement(nList, i);
                NodeList items = e.getElementsByTagName("item");
                for (j = 0; j < items.getLength(); j++) {
                    String name = items.item(j).getAttributes().getNamedItem("name").getNodeValue();
                    String nameContent = items.item(j).getTextContent();
                    if (name.equals("username")){
                        if (nameContent.equals(username)) continue;
                        else break;
                    }
                    else if(name.equals("password")){
                        if (nameContent.equals(password)) return true;
                        else {
                            System.out.println("Password not match");
                            return false;
                        }
                    }
                }
            }
            if (i==nList.getLength()) System.out.println("User not found");
        }
        return false;
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
