package CZ2002;

class MovieInfo {

    public final String title;
    public String synopsis, director, cast, movieType;

    /* Constructor */
    public MovieInfo(String title) {
        this(title, "-", "-", "-", "-");
    }

    public MovieInfo(String title, String synopsis, String director, String cast,
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
