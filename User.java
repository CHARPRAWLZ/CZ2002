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
    * Returns the UserID of the User instance.
    * @return userId
    */
   public String getUserID(){
        return userId;
}
   public String getName(){
        return name;
   }
   public String getEmail(){
        return emailAddress;   
   }
   public String getMobileNumber(){
        return mobileNo;
   }
   //mutators
   public void setName(String uname){
        name = name;
   }
   public void setEmail(String email){
        emailAddress = email;
   }
   public void setMobileNumber(String number){
        mobileNo = number;
   }
}
