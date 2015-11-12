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
    
    private User user;
    private Cineplex cineplex;
    private Cinema cinema;
    private Seat seat;
    private String ageGroup;
    private Movie movie;
    private Showtime showtime;
    private Payment payment;
    private double ticketPrice = 0;
    private XML bookingXML;
    private String [] itemName;
    
    public Booking() {
        
    }
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
        bookingXML = new XML("booking");
        this.itemName = new String[]{"tid", "custName", "custMobileNumber", "custEmail", "cineplexId", "cineplexName", "cineplexLocation", "cinemaName", "cinemaType", "seat", "ageGroup", "movieId", "movieTitle", "movieDate", "movieTime", "ticketPrice"};
        this.payment = new Payment(cinema.getName(), showtime.getDate(), 
                showtime.getTiming());
        
        switch(ageGroup) {
            case "Student":
                this.ticketPrice += 7;
                break;
            case "Adult":
                this.ticketPrice += 10;
                break;
            case "Senior Citizen":
                this.ticketPrice += 4;
        }
        if(cinema.getCinemaType().equalsIgnoreCase("Gold"))
            this.ticketPrice += 20;
        if(movie.getType().equalsIgnoreCase("3D"))
            this.ticketPrice += 3;
        /*
        bookingXML.addItem("TID", this.payment.getTID());
        //xml.addItem(e, "custId", this.user.getUserID());
        xml.addItem(e, "custName", this.user.getName());
        xml.addItem(e, "custMobileNumber", this.user.getMobileNo());
        xml.addItem(e, "custEmail", this.user.getEmail());
        xml.addItem(e, "cineplexId", this.cineplex.getID());
        xml.addItem(e, "cineplexName", this.cineplex.getName());
        xml.addItem(e, "cineplexLocation", this.cineplex.getLocation());
        xml.addItem(e, "cinemaName", this.cinema.getName());
        xml.addItem(e, "cinemaType", this.cinema.getCinemaType());
        xml.addItem(e, "seat", this.seat.getSeatID());
        xml.addItem(e, "ageGroup", this.ageGroup);
        xml.addItem(e, "movieId", this.movie.getMovieID());
        xml.addItem(e, "movieTitle", this.movie.getTitle());
        xml.addItem(e, "movieDate", this.showtime.getDate());
        xml.addItem(e, "movieTime", this.showtime.getTiming());
        xml.addItem(e, "ticketPrice", Double.toString(this.ticketPrice));
        xml.writeContent();*/
        this.objectToXml();
        
    }
    public void objectToXml() {
        String[] itemContent = new String[]{this.payment.getTID(), this.user.getName(), this.user.getMobileNo(), this.user.getEmail(), this.cineplex.getID(), this.cineplex.getName(), this.cineplex.getLocation(), this.cinema.getName(), this.cinema.getCinemaType(), this.seat.getSeatID(), this.ageGroup, this.movie.getMovieID(), this.movie.getTitle(), this.showtime.getDate(), this.showtime.getTiming(), Double.toString(this.ticketPrice)};
        bookingXML.addItem(itemName, itemContent);
        bookingXML.writeContent();
    }
}
