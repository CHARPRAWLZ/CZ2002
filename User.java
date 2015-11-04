package CZ2002;

public class User {
   private String userId, name, emailAddress, mobileNo;
   
   public User(String id,String name,String email,String no){
       this.userId = id;
       this.name = name;
       this.emailAddress = email;
       this.mobileNo = no;
   }
   public String getUserID(){
    return userId;
}
}
