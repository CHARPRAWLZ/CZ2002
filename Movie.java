package CZ2002;

/* Movie class uses an internal MovieInfo class which can be found below the 
 aforementioned class.
 */
public class Movie extends MovieInfo {

    private String movieStatus;
    private ReviewRatingList reviewList;
    private double overallRating;
    private final String movieID;

    /* Constructors */
    public Movie(String title, String movieID) {
        super(title);
        this.movieID = movieID;
        this.movieStatus = "Coming Soon";
        this.overallRating = 0;
    }

    public Movie(String title, String movieID, String synopsis, String director, String cast,
            String type, String movieStatus, double overallRating) {
        super(title, synopsis, director, cast, type);
        this.movieID = movieID;
        this.movieStatus = movieStatus;
        this.overallRating = overallRating;
    }
    /* end of Constructors */

    /* Accessors */
    /**
     * returns the status of the Movie instance
     * @return movieStatus
     */
    public String getMovieStatus() {
        return movieStatus;
    }
    /**
     * returns the movie ID of the Movie instance
     * @return movieID
     */
    public String getMovieID() {
        return movieID;
    }
    /**
     * returns the overall rating of the Movie instance
     * @return overallRating
     */
    public double getOverallRating() {
        return overallRating;
    }
    /* end of Accessors */

    /* Mutators */
    /**
     * sets the status of the movie
     * @param status 
     */
    public void setMovieStatus(String status) {
        movieStatus = status;
    }
    /**
     * set one rating of the movie
     * @param rating 
     */
    public void setOverallRating(double rating) {
        overallRating = rating;
    }
    /**
     * sets the overall rating of the movie
     * @param rating 
     */
    public void setOverallRating(double[] rating) {
        double total = 0;
        for (int i = 0; i < rating.length; i++) {
            total += rating[i];
        }
        overallRating = total / rating.length;
    }
    
    public void retrieveRating()
    /* end of Mutators */

}
