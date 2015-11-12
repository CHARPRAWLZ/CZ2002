package CZ2002;

import java.util.ArrayList;

public class MovieListing {

    private ArrayList<Movie> movieList = new <Movie> ArrayList();
    private XML xml;

    /* Constructors */
    public MovieListing() {
        XML xml = new XML("movie");

    }
    /* end of Construcors */

    /* Accessors */
    public String[] movieList() {
        String[] lists = xml.getElement();
        String[] title= null;
        for (int i = 1; i <= lists.length; i++) {
            title[i] = xml.getItemContent(lists[i - 1], "title");
            String movieType = xml.getItemContent(lists[i - 1], "movieType");
            if (!movieType.equals("normal")) {
                title[i] += " (" + movieType + ")";
            }
        }
        return title;
    }
    public String[] idList() {
        return xml.getElement();
    }

    /* end of Accessors */
    /* Mutators */
    /*
    public Movie addNewMovie() {
        Movie newMovie;
        return newMovie;
    }
    */

    /* end of Mutators */
    /*
    private void getMovieList() {

    }
    */

}
