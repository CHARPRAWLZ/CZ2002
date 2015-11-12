/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CZ2002;

//import java.util.ArrayList;
/**
 *
 * @author calvinlee
 */
public class RankingMgr {
    private XML movieXML;
    private XML bookingXML;
    private String[] movie;
    private String[] booking;
    private int[] ticket;
    private int[] topFiveIndex;
    private String[][] topFive;
    private int topTicketSales;
    private int topIndex;
    private double topOverallRatings;
    
    /**
     * Constructor for RankingMgr
     */
    public RankingMgr() {
        
    }
    
    /**
     * Returns a 2D array that contains the top 5 Movie name and total ticket sales
     * @return 2D array that contains the top 5 Movie name and total ticket sales
     */
    public String[][] rankingTicketSales() {
        topFiveIndex = new int[5];
        topFive = new String[5][2];
        movieXML = new XML("movie");
        bookingXML = new XML("booking");
        movie = movieXML.getElement();
        booking = bookingXML.getElement();
        ticket = new int[movieXML.getElement().length];
        for(int i = 0; i < movie.length; i++) {
            int count = 0;
            for(int j = 0; j < bookingXML.getElement().length; j++) {
                if(bookingXML.getItemContent(booking[j], "movieId").equals
        (movieXML.getItemContent(movie[i], "movieId"))) {
                    count++;
                }
            }
            ticket[i] = count;
        }
        for(int i = 0; i < 5; i++) {
            topTicketSales = 0;
            topIndex = 0;
            for(int j = 0; j < ticket.length; j++) {
                if(ticket[j] > topTicketSales) {
                    topTicketSales = ticket[j];
                    topIndex = j;
                }
            }
            topFiveIndex[i] = topIndex;
            ticket[topIndex] = 0;
        }
        for(int i = 0; i < 5; i++) {
            topFive[i][0] = movieXML.getItemContent(movie[topFiveIndex[i]], "movieName");
            topFive[i][1] = Integer.toString(ticket[topFiveIndex[i]]);
        }
        return topFive;
    }
    
    /**
     * Returns a 2D array that contains the top 5 Movie name and overall review ratings
     * @return 2D array that contains the top 5 Movie name and overall review ratings
     */
    public String[][] rankingReviewsRating() {
        topFiveIndex = new int[5];
        topFive = new String[5][2];
        movieXML = new XML("movie");
        movie = movieXML.getElement();
        for(int i = 0; i < 5; i++) {
            topOverallRatings = 0;
            topIndex = 0;
            for(int j = 0; j < ticket.length; j++) {
                if(Double.parseDouble(movieXML.getItemContent(movie[j], "overallRating")) > topOverallRatings) {
                    topOverallRatings = Double.parseDouble(movieXML.getItemContent(movie[j], "overallRating"));
                    topIndex = j;
                }
            }
            topFiveIndex[i] = topIndex;
            movie[topIndex] = "";
        }
        for(int i = 0; i < 5; i++) {
            topFive[i][0] = movieXML.getItemContent(movie[topFiveIndex[i]], "movieName");
            topFive[i][1] = movieXML.getItemContent(movie[topFiveIndex[i]], "overallRating");
        }
        return topFive;
    }
}
