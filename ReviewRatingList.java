package CZ2002;

import java.util.ArrayList;

public class ReviewRatingList {
    
    private XML xml;
    private final String movieID;
    private ArrayList <ReviewRating> reviewList = new <ReviewRating> ArrayList();
    private int count = 0;
    private double overallRating = 0;
    
    public ReviewRatingList(String movieID){
        this.movieID = movieID;
        this.xml = new XML("movieRating");
    }
    
    public ReviewRatingList(Movie movie){
        this.movieID = movie.getMovieID();
        this.generateList(this.movieID);
    }
    
    public void addReview(String review, int rating){
        // ArrayList update, add new review
        ReviewRating newReview = new ReviewRating(this.movieID, review, rating);
        reviewList.add(newReview);
        
        // XML update, add new review
        String[] str = new String[]{"movieID", "review", "rating"};
        //String str = "movieID";
        xml.createEntry(str);
        xml.enterEntryItem("movieID", movieID);
        xml.enterEntryItem("review", review);
        xml.enterEntryItem("rating", String.valueOf(rating));
        xml.writeContent();
    }
    
    private void generateList(String movieID){
        xml = new XML("movieRating");
        String review;
        double rating;
        
    }
    
    public static void main(String[] args){
        //XML xml = new XML("movieRating");
        ReviewRatingList reviewList = new ReviewRatingList("Wonderland");
        String review = "this is my second review.";
        int rating = 6;
        reviewList.addReview(review, rating);
    }
    
}
