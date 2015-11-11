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
    XML userXml;

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
     * @param DOB
     */
    public User() {
        userXml = new XML("user");
    }

    public User(String username, String password, String name, String email, String mobileNo) {
        userXml = new XML("user");
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;

        Element e = userXml.addElement();
        userXml.addItem(e, "username", this.username);
        userXml.addItem(e, "password", this.password);
        userXml.addItem(e, "name", this.name);
        userXml.addItem(e, "email", this.email);
        userXml.addItem(e, "mobileNo", this.mobileNo);
        userXml.writeContent();

    }

    //accessors
    /**
     * Returns the username of the user account
     *
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user account
     *
     * @return password
     */
    public String getPassword() {
        return password;
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
        return email;
    }

    /**
     * returns the mobile number of the User instance
     *
     * @return mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    //mutators
    /**
     * sets the username of the user account
     *
     * @param user
     */
    public void setUsername(String user) {
        username = user;
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
        email = email;
    }

    /**
     * set the user's mobile number
     *
     * @param number
     */
    public void setMobileNo(String number) {
        mobileNo = number;
    }

    //methods for user
    public boolean checkLogin(String username, String password) {
        //check if username and password exists in xml, if yes, return true, else, false
        int i, j;
        if (!userXml.getFile().exists()) {
            System.out.println("No file found");
        }
        else if(userXml.getElementCount("username", username)==0){
            System.out.println("User not found");
        }
        else {
            NodeList nList = userXml.getDoc().getElementsByTagName("user");
            for (i = 0; i < nList.getLength(); i++) {
                Element e = userXml.getNodeElement(nList, i);
                NodeList items = e.getElementsByTagName("item");
                for (j = 0; j < items.getLength(); j++) {
                    String name = items.item(j).getAttributes().getNamedItem("name").getNodeValue();
                    String nameContent = items.item(j).getTextContent();
                    if (name.equals("password")) {
                        if (nameContent.equals(password)) {
                            this.xmlToObject(e.getAttribute("id"));
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

    public void xmlToObject(String id) {
        NodeList nList = userXml.getDoc().getElementsByTagName("user");
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = userXml.getNodeElement(nList, i);
            NodeList items = e.getElementsByTagName("item");
            if (e.getAttribute("id").equals(id)) {
                this.id = e.getAttribute("id");
                for (int j = 0; j < items.getLength(); j++) {
                    String name = items.item(j).getAttributes().getNamedItem("name").getNodeValue();
                    String nameContent = items.item(j).getTextContent();
                    switch (name) {
                        case "username":
                            this.username = nameContent;
                            break;
                        case "password":
                            this.password = nameContent;
                            break;
                        case "name":
                            this.name = nameContent;
                            break;
                        case "email":
                            this.email = nameContent;
                            break;
                        case "mobileNo":
                            this.mobileNo = nameContent;
                            break;
                    }
                    /*
                     if (name.toLowerCase().equals("username")) this.username = nameContent;
                     else if (name.toLowerCase().equals("password")) this.password = nameContent;
                     else if (name.toLowerCase().equals("name")) this.name = nameContent;
                     else if (name.toLowerCase().equals("email")) this.email = nameContent;
                     else if (name.toLowerCase().equals("mobileNo")) this.mobileNo = nameContent;
                     else if (name.toLowerCase().equals("dob")) this.dob = nameContent;
                     */
                }
                break;
            }
        }

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
