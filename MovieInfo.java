package CZ2002;
import java.util.ArrayList;

class MovieInfo {

    private final String title;
    private String synopsis, director, cast, movieType;

    /* Constructor */
    public MovieInfo(String title) {
        this(title, "", "", "", "");
    }

    /**Constructor of basic movie info
     * 
     * @param title
     * @param synopsis
     * @param director
     * @param cast
     * @param movieType 
     */
    public MovieInfo(String title, String synopsis, String director, String cast,
            String movieType) {
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.movieType = movieType;
        this.cast = cast;
    }
    /* end of Constructor */

    /* Accessors */
    /**Returns the title name of the movie.
     * 
     * @return title of movie (String)
     */
    public String getTitle() {
        return title;
    }

    /**Returns Synopsis of the movie.
     * 
     * @return synopsis of movie (String)
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**Returns director name of the movie.
     * 
     * @return director name of movie (String)
     */
    public String getDirector() {
        return director;
    }

    /**Returns the casts of the movie.
     * 
     * @return A single string containing info of the casts of the movie.
     */
    public String getCast() {
        return cast;
    }

    /**Returns the movie type.
     * Example: 3D, blockbuster, etc.
     * 
     * @return Type of movie
     */
    public String getType() {
        return movieType;
    }
    /* end of Accessors */

    /* Mutators */
    /**Sets the synopsis of the movie.
     * 
     * @param synopsis 
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**Sets the name of the director of the movie.
     * 
     * @param director 
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**Sets the casts of the movie. It is saved as a single String.
     * This will replace the existing casts data.
     * @param cast 
     */
    public void setCast(String cast) {
        this.cast = cast;
    }
    
    /**Adds additional cast name to the end of existing cast data.
     * 
     * @param extraCast 
     */
    public void addCast(String extraCast) {
        this.cast = this.cast + ", " + extraCast;
    }

    /**Sets the type of the movie.
     * 
     * @param movieType 
     */
    public void setType(String movieType) {
        this.movieType = movieType;
    }
    /* end of Mutators */

}
