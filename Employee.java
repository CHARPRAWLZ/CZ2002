package CZ2002;
/*
author: zach
*/
import java.util.Scanner;
public class Employee extends User{
    //variables
    private String userName, password;
    //constructor for employee
    /**
    * Constructs an instance of class Employee.
    * @param user
    * @param  pw
    */
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
    public void configureTickets(){}
    public void configureMovies(){
        String status = sc.next();
        movie.setMovieStatus(status);
    }
    public void configureTop5movies(){
        double rating = sc.nextDouble();
        movie.setOverallRating(rating);
    }
    public void configureMovieListing(){
       movie.getMovieStatus();
    }
    
}
