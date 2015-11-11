/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CZ2002;

/**
 *
 * @author calvinlee
 */
public class Ticket {
    
    private String cinemaType;
    private double ticketPrice;
    private String movieType;
    private String bookingDate;
    private String ageGroup;
    
    public Ticket(String cinemaType, String movieType, String bookingDate, 
            int age) {
        this.cinemaType = cinemaType;
        this.movieType = movieType;
        this.bookingDate = bookingDate;
        if(age <= 12)
            ageGroup = "Child";
        else if(age < 60)
            ageGroup = "Adult";
        else
            ageGroup = "Senior Citizen";
    }
    
}
