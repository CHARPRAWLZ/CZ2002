package CZ2002;
import java.util.*;
public class Employee extends User{
    //variables
    private String userName, password;
    //constructor for employee
    public Employee(String user, String pw, String id, String name, String email,String number){
        super(id,name,email,number);
        this.userName = user;
        this.password = pw;
    }
    //accessors
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    //mutators
    public void setUserName(String user){
        userName = user;
    }
    public void setPassword(String pw){
        password = pw;
    }
    
    //methods for employee
    public Movie movie;
    Scanner sc = new Scanner(System.in);
        
    public void configureTickets(){
        //add, remove, update tickets from ticket class
    }
    public void configureMovies(){
       //add, remove, update movies from xml
    }
    public void configureTop5movies(){
        /*
        sort all movie overall rating
        print top 5 movies
        */
    }
    public void configureMovieListing(){
       /*
        
        */
    }
    
}
