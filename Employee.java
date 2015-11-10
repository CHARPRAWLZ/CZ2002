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
    /**
     * Returns the username of the employee account
     * @return userName
     */
    public String getUserName(){
        return userName;
    }
    /**
     * Returns the password of the employee account
     * @return password
     */
    public String getPassword(){
        return password;
    }
    //mutators
    /**
     * sets the username of the employee account
     * @param user 
     */
    public void setUserName(String user){
        userName = user;
    }
    /**
     * sets the password of the employee account
     * @param pw 
     */
    public void setPassword(String pw){
        password = pw;
    }
    
    //methods for employee
    public Movie movie;
    Scanner sc = new Scanner(System.in);
    /**
     * add/remove/update tickets available for the movie
     */
    public void configureTickets(){}
    /**
     * add/remove/update movies available
     */
    public void configureMovies(){
        String status = sc.next();
        movie.setMovieStatus(status);
    }
    /**
     * prints the top 5 movie rating
     */
    public void configureTop5movies(){
        double rating = sc.nextDouble();
        movie.setOverallRating(rating);
    }
    /**
     * add/remove movie timings
     */
    public void configureMovieListing(){
       movie.getMovieStatus();
    }
    
}
