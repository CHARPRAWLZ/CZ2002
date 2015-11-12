package CZ2002;

import java.util.ArrayList;

public class ReviewRatingList {
    
    private XML xml;
    private final String movieID;
    private ArrayList <ReviewRating> reviewList = new <ReviewRating> ArrayList();
    private int count = 0;
    private double total = 0;
    private double overallRating = 0;
    
    /* Constructors */
    public ReviewRatingList(String movieID){
        this.movieID = movieID;
        this.xml = new XML("movieRating");
        generateList(this.movieID);
    }
    
    public ReviewRatingList(Movie movie){
        this(movie.getMovieID());
    }
    /* end of Constructors */
    
    /* Accessors */
    public ArrayList <ReviewRating> getListArr(){
        return reviewList;
    }
    
    public double getOverallRating(){
        return this.overallRating;
    }
    /* end of Accessors */
    
    /* Mutators */
    public void addReview(String review, int rating){
        addToArrayList(review, rating);
        
        // XML update, add new review
        String[] str = new String[]{"movieID", "review", "rating"};
        xml.createEntry(str);
        xml.enterEntryItem("movieID", movieID);
        xml.enterEntryItem("review", review);
        xml.enterEntryItem("rating", String.valueOf(rating));
        xml.writeContent();
    }
    
    private void addToArrayList(String review, int rating){
        addToArrayList(this.movieID, review, rating);
    }
    
    private void addToArrayList(String movieID, String review, int rating){
        ReviewRating newReview = new ReviewRating(this.movieID, review, rating);
        reviewList.add(newReview);
        
        this.total += rating;
        this.count++;
        this.overallRating = this.total / this.count;
    }
    
    private void generateList(String movieID){
        ReviewRating reviewEntry;
        String review = "";
        int rating = 0;
        boolean movieFlag, reviewFlag, ratingFlag;
        String retrievedList[][] = xml.retrieveData(this.movieID);
        for (String[] subList : retrievedList) {
            movieFlag = false;
            reviewFlag = ratingFlag = true;
            for (int j = 0; j < subList.length; j+=2) {
                if (subList[j].equals("movieID")) {
                    movieFlag = true;
                } else if (reviewFlag && subList[j].equals("review")) {
                    review = subList[j+1];
                    reviewFlag = false;
                } else if (ratingFlag && subList[j].equals("rating")) {
                    rating = Integer.parseInt(subList[j+1]);
                    ratingFlag = false;
                } else {
                    System.out.println("\n\nError in generating Review Rating "
                            + "List.");
                    review = "";
                    rating = 0;
                }
            }
            
            if (movieFlag && !(reviewFlag || ratingFlag)){
                addToArrayList(movieID, review, rating);
            }
            else {
                System.out.println("\n\n2Error in generating Review Rating "
                        + "List.");
                return;
            }
        }
    }
    /* end of Mutators */
    
    public static void main(String[] args){
        //System.out.println("start");
        String movieID = "X_MEN";
        ReviewRatingList reviewList = new ReviewRatingList(movieID);
        ArrayList <ReviewRating> reviewListArr = reviewList.getListArr();
        
        //System.out.println("start 2");
        
        for (ReviewRating rr : reviewListArr){
            System.out.println("========================");
            System.out.println("movieID : " + rr.getMovieID());
            System.out.println("review  : " + rr.getReview());
            System.out.println("rating  : " + rr.getRating());
        }
        
        /*String review = "This is a third review.";
        int rating = 7;
        reviewList.addReview(review, rating);*/
    }
    
}
