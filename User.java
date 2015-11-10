package CZ2002;
/*
author: zach
*/
public class User {
    //variables
   private String userId, name, emailAddress, mobileNo;
   //constructors
   /**
    * Constructs an instance of class User.
    * @param id
    * @param name
    * @param email
    * @param number 
    */
   public User(String id,String name,String email,String number){
       this.userId = id;
       this.name = name;
       this.emailAddress = email;
       this.mobileNo = number;
   }
   //accessors
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
   //mutators
   /**
    * set the user's name
    * @param uname 
    */
   public void setName(String uname){
        name = name;
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
}
