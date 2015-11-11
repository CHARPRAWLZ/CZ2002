package CZ2002;

import java.util.Scanner;
/*
author: zach
*/
import java.util.Scanner;
public class Employee extends User{
    //constructor for employee
    /**
    * Constructs an instance of class Employee.
    * @param user
    * @param pw
    * @param id
    * @param name
    * @param email
    * @param number
    * @param age
    */
    public Employee(String user, String pw, String id, String name, String email,String number,int age){
        super(user,pw,id,name,email,number,age);
        
    }
    
    //methods for employee
    public Movie movie;
    Scanner sc = new Scanner(System.in);
        
    public void configureTickets(){}
    /**
     * add/remove/update movies available
     */

    public void configureMovies(){
       //add, remove, update movies from xml
    }
    /**
     * prints the top 5 movie rating
     */
    public void configureTop5movies(){
        /*
        sort all movie overall rating
        print top 5 movies
        */
    }
    /**
     * add/remove movie timings
     */
    public void configureMovieListing(){
       /*
        
        */
    }
    
}
