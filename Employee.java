package CZ2002;
import java.util.Scanner;
public class Employee extends User{
    private String userName, password;
    public Employee(String user, String pw, String id, String name, String email,String no){
        super(id,name,email,no);
        this.userName = user;
        this.password = pw;
    }
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
    
    
    //
    //testing
}
