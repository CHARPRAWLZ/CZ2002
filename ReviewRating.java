package CZ2002;

public class ReviewRating {
    
    private String movieID;
    private int rating;
    private String review;
    
    public ReviewRating(String movieID, String review, int rating){
        this.movieID = movieID;
        this.review = review;
        this.rating = rating;
    }
    
    public String getMovieID(){
        return this.movieID;
    }
    
    public String getReview(){
        return this.review;
    }
    
    public double getRating(){
        return this.rating;
    }
    
}
