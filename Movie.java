package CZ2002;

/* Movie class uses an internal MovieInfo class which can be found below the 
 aforementioned class.
 */
public class Movie extends movieInfo {

    private String movieStatus;
    private double overallRating;
    private final String movieID;

    /* Constructors */
    Movie(String title, String movieID) {
        super(title);
        this.movieID = movieID;
        this.movieStatus = "Coming Soon";
        this.overallRating = 0;
    }

    Movie(String title, String movieID, String synopsis, String director, String cast,
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

class movieInfo {

    public final String title;
    public String synopsis, director, cast, movieType;

    /* Constructor */
    movieInfo(String title) {
        this(title, "-", "-", "-", "-");
    }

    movieInfo(String title, String synopsis, String director, String cast,
            String movieType) {
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.movieType = movieType;
    }
    /* end of Constructor */

    /* Accessors */
    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getType() {
        return movieType;
    }
    /* end of Accessors */

    /* Mutators */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setType(String movieType) {
        this.movieType = movieType;
    }
    /* end of Mutators */

}
