/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CZ2002;

import org.w3c.dom.*;

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
    private Showtime showtime;
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
    public Showtime getShowtime() {
        return this.showtime;
    }
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
    public void confirmBooking() {
        XML xml = new XML("booking");
        Element e = xml.addElement();
        Payment payment = new Payment(cinema.getName(), showtime.getDate(), 
                showtime.getTiming());
        xml.addItem(e, "TID", payment.getTID());
        xml.addItem(e, "custId", user.getUserID());
        xml.addItem(e, "custName", user.getName());
        xml.addItem(e, "custMobileNumber", user.getMobileNumber());
        xml.addItem(e, "custEmail", user.getEmail());
        xml.addItem(e, "cineplexId", cineplex.getID());
        xml.addItem(e, "cineplexName", cineplex.getName());
        xml.addItem(e, "cineplexLocation", cineplex.getLocation());
        xml.addItem(e, "cinemaName", cinema.getName());
        xml.addItem(e, "cinemaType", cinema.getCinemaType());
        xml.addItem(e, "seat", seat.getSeatID());
        xml.addItem(e, "movieId", movie.getMovieID());
        xml.addItem(e, "movieTitle", movie.getTitle());
        xml.addItem(e, "movieDate", showtime.getDate());
        xml.addItem(e, "movieTime", showtime.getTiming());
        // need to calculate ticket price
    }
}
