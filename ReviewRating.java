package CZ2002;

public class ReviewRating {
    
    private String movieID;
    private int rating;
    private String review;
    
    public ReviewRating(String movieID, String review, int rating){
        this.review = review;
        this.rating = rating;
    }
    
    public String getReview(){
        return review;
    }
    
    public double getRating(){
        return rating;
    }
    
}
