package CZ2002;

import java.util.ArrayList;

public class MovieListing {

    private ArrayList<Movie> movieList = new <Movie> ArrayList();
    private final XML xml;
    private final String[][] itemName;

    /* Constructors */
    public MovieListing() {
        xml = new XML("movie");
        this.itemName = new String[][]{{"title","synopsis","director","cast","movieType","movieStatus","overallRating"},
            {"Title","Synopsis","Director","Cast","Movie type","Movie status","Overall Rating"}};

    }
    /* end of Construcors */

    /* Accessors */
    /**
     * Get a list of movies by movie id
     * @return movie id
     */
    public String[] getMovieList() {
        String[] lists = xml.getElement();
        String[] title= new String[lists.length];
        for (int i = 0; i < lists.length; i++) {
            title[i] = xml.getItemContent(lists[i], "title");
            String movieType = xml.getItemContent(lists[i], "movieType");
            if (!movieType.equals("normal")) {
                title[i] += " (" + movieType + ")";
            }
        }
        return title;
    }
    public String[] getMovieInfo(String id) {
        String[] itemContent = new String[this.itemName[0].length];
        for (int i=0;i<itemName[0].length;i++) {
            itemContent[i] = xml.getItemContent(id, this.itemName[0][i]);
        }
        return itemContent;
    }
    public String[] idList() {
        return xml.getElement();
    }
    public String[][] getItemName() {
        return this.itemName;
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
