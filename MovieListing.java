package CZ2002;

import java.util.*;

public class MovieListing {

    private ArrayList<Movie> movieList = new <Movie> ArrayList();
    private final XML xml;
    private final String[][] itemName;
    private Scanner sc;

    /* Constructors */
    public MovieListing() {
        sc = new Scanner(System.in);
        xml = new XML("movie");
        this.itemName = new String[][]{{"title", "synopsis", "director", "cast", "movieType", "movieStatus", "overallRating"},
        {"Title", "Synopsis", "Director", "Cast", "Movie type", "Movie status", "Overall Rating"}};
        //generateList();
    }
    /* end of Construcors */

    /* Accessors */
    /**
     * Get a list of movies by movie id
     *
     * @return movie id
     */
    public String[] getMovieList() {
        String[] lists = xml.getElement();
        String[] title = new String[lists.length];
        for (int i = 0; i < lists.length; i++) {
            title[i] = xml.getItemContent(lists[i], "title");
            String movieType = xml.getItemContent(lists[i], "movieType");
            if (!movieType.equals("normal")) {
                title[i] += " (" + movieType + ")";
            }
        }
        return title;
    }

    public String[] getMovieIds() {
        return xml.getElement();
    }

    public String[] getMovieInfo(String id) {
        String[] itemContent = new String[this.itemName[0].length];
        for (int i = 0; i < itemName[0].length; i++) {
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

    public void createMovie() {

        String[] itemContent = new String[itemName[0].length];
        for (int i = 0; i < itemName[0].length; i++) {
            System.out.print("Enter " + itemName[1][i] + " : ");
            itemContent[i] = sc.nextLine();
        }
        xml.addItem(itemName[0], itemContent);
        xml.writeContent();
        this.addToArrayList(xml.getCounter(), itemContent[0], itemContent[1],
                itemContent[2], itemContent[3], itemContent[4], itemContent[5]);
        System.out.println("- Movie added into database -");
    }

    public void getMovie(String id) {
        if (xml.checkIdExists(id)) {
            String[] info = this.getMovieInfo(id);
            System.out.println("\n------------------");
            System.out.println("MOVIE ID : " + id);
            for (int j = 0; j < info.length; j++) {
                System.out.println("   " + itemName[0][j] + " : " + info[j]);
            }
        }
    }

    public void updateMovie(String id) {
        if (xml.checkIdExists(id)) {
            System.out.print("Enter item name: ");
            String name = sc.nextLine();
            if (xml.checkItemExists(id, name)) {
                System.out.print("Enter new value: ");
                String val = sc.nextLine();
                System.out.println(name);
                System.out.println(val);
                xml.editItem(id, name, val);
                xml.writeContent();
                getMovie(id);
                System.out.println("- Movie has been updated -");
            }
        }
    }
    public void deleteMovie(String id) {
        if (xml.checkIdExists(id)) {
                xml.editItem(id, "movieStatus", "End Of Showing");
                xml.writeContent();
                getMovie(id);
                System.out.println("- Movie has been deleted -");
            
        }
    }

    public void listAllMovies() {
        String[] movieIds = this.getMovieIds();
        for (int i = 0; i < movieIds.length; i++) {
            System.out.println("\n------------------");
            System.out.println("MOVIE ID : " + movieIds[i]);
            String[] info = this.getMovieInfo(movieIds[i]);
            for (int j = 0; j < info.length; j++) {
                System.out.println("   " + itemName[0][j] + " : " + info[j]);
            }
        }
    }

    public void listByContent(String name, String content) {
        ArrayList<String> ids = xml.getElement(name, content);
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("\n------------------");
            System.out.println("MOVIE ID : " + ids.get(i));
            String[] info = this.getMovieInfo(ids.get(i));
            for (int j = 0; j < info.length; j++) {
                System.out.println("   " + itemName[0][j] + " : " + info[j]);
            }
        }
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
            String director, String cast, String type, String movieStatus) {
        Movie movie = new Movie(movieID, title, synopsis, director, cast, type,
                movieStatus);
        movieList.add(movie);
    }

    public void addToArrayList(String movieID, String title) {
        Movie movie = new Movie(movieID, title);
        movieList.add(movie);
    }

    private void generateList() {
        Movie movieEntry;

        // variables of Movie
        String movieID, title, synopsis, director, cast, type, movieStatus;
        movieID = title = synopsis = director = cast = type = movieStatus = "";

        // flags of variables to ensure data entry once per variable
        boolean movieIDflag, titleFlag, synopsisFlag, directorFlag, castFlag,
                typeFlag, statusFlag;

        // retrive 2D array list of variable data
        String retrievedList[][] = xml.retrieveData("None");

        for (String[] subList : retrievedList) {

            // empty all Strings
            movieID = title = synopsis = director = cast = type = movieStatus
                    = "";

            // open flag gates
            movieIDflag = titleFlag = synopsisFlag = directorFlag = castFlag
                    = typeFlag = statusFlag = true;

            // close flag gates upon successful write
            for (int j = 0; j < subList.length; j += 2) {
                if (movieIDflag && subList[j].equals("movieID")) {
                    movieIDflag = false;
                } else if (titleFlag && subList[j].equals("title")) {
                    title = subList[j + 1];
                    titleFlag = false;
                } else if (synopsisFlag && subList[j].equals("synopsis")) {
                    synopsis = subList[j + 1];
                    synopsisFlag = false;
                } else if (directorFlag && subList[j].equals("director")) {
                    director = subList[j + 1];
                    directorFlag = false;
                } else if (castFlag && subList[j].equals("cast")) {
                    cast = subList[j + 1];
                    castFlag = false;
                } else if (typeFlag && subList[j].equals("type")) {
                    type = subList[j + 1];
                    typeFlag = false;
                } else if (statusFlag && subList[j].equals("movieStatus")) {
                    movieStatus = subList[j + 1];
                    statusFlag = false;
                } else {
                    System.out.println("\n\nError in generating Review Rating "
                            + "List.");
                }
            }

            // as long as these variables and written, append to ArrayList
            if (!(movieIDflag || titleFlag)) {
                addToArrayList(movieID, title, synopsis, director, cast, type,
                        movieStatus);
            } else {
                System.out.println("\n\n2Error in generating Review Rating "
                        + "List.");
                return;
            }
        }
    }

    public Movie findMovie(String movieID) {
        for (Movie m : movieList) {
            if (m.getMovieID().equals(movieID)) {
                return m;
            }
        }
        return null;
    }

}
