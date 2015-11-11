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
public class Booking {
    /*
    private String custName;
    private String custMobileNumber;
    private String custEmailAddr;
    private int custAge;*/
    private User user;
    /*
    private String cineplexId;
    private String cineplexName;
    private String cinemaName;
    private String cinemaType;
    private String seat;
    private String movieId;
    private String movieName;
    private String movieDate;
    private String movieTime;
    private Payment payment;*/
    
    private Cineplex cineplex;
    /*
    private String cineplexId;
    private String cineplexName;*/
    private Cinema cinema;
    private Seat seat;
    /*
    private String cinemaName;
    private String cinemaType;
    private String seat;*/
    private Movie movie;
    /*
    private String movieId;
    private String movieTitle;
    private String movieDate;
    private String movieTime;*/
    private Payment payment;
    /*
    public Booking(String custName, String custMobileNumber, 
            String custEmailAddr, int custAge, String cineplexId, 
            String cineplexName, String cinemaName, String cinemaType, 
            String seat, String movieId, String movieName, String movieDate, 
            String movieTime) {
        this.custName = custName;
        this.custMobileNumber = custMobileNumber;
        this.custEmailAddr = custEmailAddr;
        this.custAge = custAge;
        payment = new Payment(cinemaName, movieDate, movieTime);
    }*/
    public Booking() {
        
    }
    /*
    i'm about to add in set methods to read in data and save to the instance
   public void setCustName(String customerName){
       custName = customerName;
   }
   public void setCustMobileNumber(String phoneNumber){
       custMobileNumber = phoneNumber;
   }
    to be continued...
  */
    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Cineplex getCineplex() {
        return this.cineplex;
    }
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
    public Cinema getCinema() {
        return this.cinema;
    }
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    public Seat getSeat() {
        return this.seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public void confirmBooking() {
        
    }
}
