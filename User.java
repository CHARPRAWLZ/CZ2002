package CZ2002;

public class User {
   private String userId, name, emailAddress, mobileNo;
   
   public User(String id,String name,String email,String number){
       this.userId = id;
       this.name = name;
       this.emailAddress = email;
       this.mobileNo = number;
   }
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
