/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CZ2002;

//import org.w3c.dom.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class shows the booking of tickets
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
    private XML holidayXML;
    private String[] itemName;
    
    public Booking() {
        
    }
    /**
     * shows the user that is booking
     * @return user 
     */
    public User getUser() {
        return this.user;
    }
    /**
     * Change the user that is booking
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * shows the Cineplex to book
     * @return cineplex
     */
    public Cineplex getCineplex() {
        return this.cineplex;
    }
    /**
     * Change the Cineplex to book
     * @param cineplex 
     */
    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
    /**
     * shows the cinema to book
     * @return cinema
     */
    public Cinema getCinema() {
        return this.cinema;
    }/**
     * Change the cinema to book
     * @param cinema 
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    /**
     * shows the seat that's being booked 
     * @return seat
     */
    public Seat getSeat() {
        return this.seat;
    }
    /**
     * Change the seat to book
     * @param seat 
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    /**
     * shows the movie that's being booked
     * @return movie
     */
    public Movie getMovie() {
        return this.movie;
    }
    /**
     * change the movie that's being booked
     * @param movie 
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    /**
     * shows the booked movie time
     * @return 
     */
    public Showtime getShowtime() {
        return this.showtime;
    }
    /**
     * change the movie timing 
     * @param showtime 
     */
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
    /**
     * Includes additional information to compute surcharges of ticket prices
     * Checks if :
     * 1)movie falls on a holiday,
     * 2)movie goer is a child/adult/senior citizen
     * 3)the cinema is a regular or a gold class
     */
    public void confirmBooking() {
        bookingXML = new XML("booking");
        holidayXML = new XML("holiday");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(cal.getTime());
        this.itemName = new String[]{"tid", "custId", "custName", "custMobileNumber", "custEmail", "cineplexId", "cineplexName", "cineplexLocation", "cinemaName", "cinemaType", "seat", "ageGroup", "movieId", "movieTitle", "movieDate", "movieTime", "ticketPrice"};
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
        for(int i = 0; i < holidayXML.getElement().length; i++) {
            if(formatted.equals(holidayXML.getItemContent(holidayXML.getElement()[i], "date")))
                this.ticketPrice += 3;
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
    /**
     * saves to the database
     */
    public void objectToXml() {
        String[] itemContent = new String[]{this.payment.getTID(), this.user.getUserId(), this.user.getName(), this.user.getMobileNo(), this.user.getEmail(), this.cineplex.getID(), this.cineplex.getName(), this.cineplex.getLocation(), this.cinema.getName(), this.cinema.getCinemaType(), this.seat.getSeatID(), this.ageGroup, this.movie.getMovieID(), this.movie.getTitle(), this.showtime.getDate(), this.showtime.getTiming(), Double.toString(this.ticketPrice)};
        bookingXML.addItem(itemName, itemContent);
        bookingXML.writeContent();
    }
}
