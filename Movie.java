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
    public Movie(String movieID, String title, String synopsis, String director, String cast,
            String type, String movieStatus) {
        super(title, synopsis, director, cast, type);
        this.movieID = movieID;
        this.movieStatus = movieStatus;
        this.reviewList = new ReviewRatingList(movieID);
        this.overallRating = this.reviewList.getOverallRating();
    }
    
    public Movie(String movieID, String title) {
        this(movieID, title, "", "", "", "", "Coming Soon");
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
    /* end of Mutators */

    public static void main(String[] args){
        
        Movie movie = new Movie("X_MEN", "X-MEN", "My Synopsis", "Zad", "Hocks,"
                + " Cal, Fiefie", "Blockbuster", "Coming Soon");
        
        System.out.println("Movie title: " + movie.getTitle());
        System.out.println("Overall Rating: " + movie.getOverallRating());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Casts   : " + movie.getCast());
        
    }
}
