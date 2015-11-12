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
    private String[] ticket;
    private double[] rating;
    public RankingMgr() {
        movieXML = new XML("movie");
        bookingXML = new XML("booking");
        movie = movieXML.getElement();
        ticket = new String[movieXML.getElement().length];
        rating = new double[movieXML.getElement().length];
    }
    public void rankingTicketSales() {
        for(int i = 0; i < movie.length; i++) {
            
        }
    }
    public void rankingReviewsRating() {
        
    }
}
