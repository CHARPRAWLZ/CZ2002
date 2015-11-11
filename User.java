package CZ2002;
/*
author: zach
*/
public class User {
    //variables
   private String userId, name, emailAddress, mobileNo, userName, password;
   private int age;
   //constructors
   /**
    * Constructs an instance of class User.
    * @param user
    * @param pw
    * @param id
    * @param name
    * @param email
    * @param number 
    * @param age
    */
   public User(String user, String pw, String id, String name, String email,String number,int age){
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
     * @return userName
     */
    public String getUserName(){
        return userName;
    }
    /**
     * Returns the password of the user account
     * @return password
     */
    public String getPassword(){
        return password;
    }
    //mutators
    
   /**
    * returns the UserID of the User instance.
    * @return userId
    */
   public String getUserID(){
        return userId;
}
   /**
    * returns the name of User instance
    * @return name
    */
   public String getName(){
        return name;
   }
   /**
    * returns the email address of the User instance
    * @return emailAddress
    */
   public String getEmail(){
        return emailAddress;   
   }
   /**
    * returns the mobile number of the User instance
    * @return mobileNo
    */
   public String getMobileNumber(){
        return mobileNo;
   }
   /**
    * returns the age of the user
    * @return age
    */
   public int getAge(){
       return age;
   }
   //mutators
  /**
   * sets the username of the user account
   * @param user 
   */
    public void setUserName(String user){
        userName = user;
    }
   /**
    * sets the password of the user account
    * @param pw 
    */
    public void setPassword(String pw){
        password = pw;
    }
   /**
    * set the user's name
    * @param uname 
    */
   public void setName(String myName){
        name = myName;
   }
   /**
    * set the user's email
    * @param email 
    */
   public void setEmail(String email){
        emailAddress = email;
   }
   /**
    * set the user's mobile number
    * @param number 
    */
   public void setMobileNumber(String number){
        mobileNo = number;
   }
   /**
    * sets the user's age in terms of years
    * @param years 
    */
   public void setAge(int years){
       age = years;
   }
   //methods for user
   public Movie movie;
   /**
    * user rates a certain movie
    * @param rating
    */
   public void rateMovie(double rating){
       movie.setOverallRating(rating);
   }
   /**
    * user reviews a movie
    */
   public void reviewMovie(){}
   /**
    * user books a movie
    */
   public void bookMovie(){}
}
