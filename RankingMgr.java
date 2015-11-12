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
    private double[] rating;
    private int[] topFiveIndex;
    private String[][] topFive;
    private int top;
    private int topIndex;
    public RankingMgr() {
        
    }
    public String[][] rankingTicketSales() {
        topFiveIndex = new int[5];
        topFive = new String[5][2];
        movieXML = new XML("movie");
        bookingXML = new XML("booking");
        movie = movieXML.getElement();
        booking = bookingXML.getElement();
        ticket = new int[movieXML.getElement().length];
        rating = new double[movieXML.getElement().length];
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
            top = 0;
            topIndex = 0;
            for(int j = 0; j < ticket.length; j++) {
                if(ticket[i] > top) {
                    top = ticket[i];
                    topIndex = i;
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
    public void rankingReviewsRating() {
        
    }
}
