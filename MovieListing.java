package CZ2002;

import java.util.ArrayList;

public class MovieListing {

    private ArrayList <Movie> movieList = new <Movie> ArrayList();
    private final XML xml;
    private final String[] itemName;

    /* Constructors */
    public MovieListing() {
        this.xml = new XML("movie");
        generateList();
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
    public void addNewMovie(String movieID, String title, String synopsis,
            String director, String cast, String type, String movieStatus) {
        addToArrayList(movieID, title, synopsis, director, cast, type,
                movieStatus);
        
        String[] str = new String[]{"movieID", "title", "synopsis", "director", 
            "cast", "type", "movieStatus"};
        xml.createEntry(str);
        xml.enterEntryItem("movieID", movieID);
        xml.enterEntryItem("title", title);
        xml.enterEntryItem("synopsis", synopsis);
        xml.enterEntryItem("director", director);
        xml.enterEntryItem("cast", cast);
        xml.enterEntryItem("type", type);
        xml.enterEntryItem("movieStatus", movieStatus);
    }
    
    public void addToArrayList(String movieID, String title, String synopsis,
            String director, String cast, String type, String movieStatus){
        Movie movie = new Movie(movieID, title, synopsis, director, cast, type, 
                movieStatus);
        movieList.add(movie);
    }
    
    public void addToArrayList(String movieID, String title){
        Movie movie = new Movie(movieID, title);
        movieList.add(movie);
    }
    
    private void generateList(){
        Movie movieEntry;
        
        // variables of Movie
        String movieID, title, synopsis, director, cast, type, movieStatus;
        movieID = title = synopsis = director = cast = type = movieStatus = "";
        
        // flags of variables
        boolean movieIDflag, titleFlag, synopsisFlag, directorFlag, castFlag, 
                typeFlag, statusFlag;
        
        String retrievedList[][] = xml.retrieveData("None");
        for (String[] subList : retrievedList) {
            movieID = title = synopsis = director = cast = type = movieStatus 
                    = "";
            movieIDflag = titleFlag = synopsisFlag = directorFlag = castFlag =
                    typeFlag = statusFlag = true;
            for (int j = 0; j < subList.length; j+=2) {
                if (movieIDflag && subList[j].equals("movieID")) {
                    movieIDflag = false;
                } else if (titleFlag && subList[j].equals("title")) {
                    title = subList[j+1];
                    titleFlag = false;
                } else if (synopsisFlag && subList[j].equals("synopsis")) {
                    synopsis = subList[j+1];
                    synopsisFlag = false;
                } else if (directorFlag && subList[j].equals("director")){
                    director = subList[j+1];
                    directorFlag = false;
                } else if (castFlag && subList[j].equals("cast")){
                    cast = subList[j+1];
                    castFlag = false;
                } else if (typeFlag && subList[j].equals("type")){
                    type = subList[j+1];
                    typeFlag = false;
                } else if (statusFlag && subList[j].equals("movieStatus")){
                    movieStatus = subList[j+1];
                    statusFlag = false;
                } else {
                    System.out.println("\n\nError in generating Review Rating "
                            + "List.");
                }
            }
            
            if (!(movieIDflag || titleFlag)){
                addToArrayList(movieID, review, rating);
            }
            else {
                System.out.println("\n\n2Error in generating Review Rating "
                        + "List.");
                return;
            }
    }

    /* end of Mutators */
    /*
    private void getMovieList() {

    }
    */

}
