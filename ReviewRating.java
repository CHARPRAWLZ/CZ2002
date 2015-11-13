package CZ2002;
/**
 * This class shows the reviews and ratings for a movie
 * @author Zach
 */
public class ReviewRating {
    
    private String movieID;
    private int rating;
    private String review;
    /**
     * Create a review for a movie. 
     * @param movieID
     * @param review
     * @param rating 
     */
    public ReviewRating(String movieID, String review, int rating){
        this.movieID = movieID;
        this.review = review;
        this.rating = rating;
    }
    /**
     * shows the movie id
     * @return movie id
     */
    public String getMovieID(){
        return this.movieID;
    }
    /**
     * shows the review of the movie
     * @return movie review
     */
    public String getReview(){
        return this.review;
    }
    /**
     * shows the rating of the movie
     * @return movie rating
     */
    public double getRating(){
        return this.rating;
    }
    
}
