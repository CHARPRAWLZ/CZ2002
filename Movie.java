package CZ2002;

/* Movie class uses an internal MovieInfo class which can be found below the 
 aforementioned class.
 */
public class Movie extends MovieInfo {

    private String movieStatus;
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
    public String getMovieStatus() {
        return movieStatus;
    }

    public String getMovieID() {
        return movieID;
    }
    
    public double getOverallRating() {
        return overallRating;
    }
    /* end of Accessors */

    /* Mutators */
    public void setMovieStatus(String status) {
        movieStatus = status;
    }

    public void setOverallRating(double rating) {
        overallRating = rating;
    }

    public void setOverallRating(double[] rating) {
        double total = 0;
        for (int i = 0; i < rating.length; i++) {
            total += rating[i];
        }
        overallRating = total / rating.length;
    }
    /* end of Mutators */

}
