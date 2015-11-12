package CZ2002;
import java.util.ArrayList;

public class ShowTimeMgr {
    
    private XML file;
    private ArrayList <Showtime> showtimes = new <Showtime> ArrayList();
    private MovieListing movieList;
    
    /* Constructors */
    public ShowTimeMgr(MovieListing movieList){
        this.movieList = movieList;
        readMovieListing(); // Generate showtime listing from XML file
    }
    /* end of Constructors */
    
    /* Accessors */
    
    
    /* end of Accessors */
    
    /* Mutators */
    // contains methods for manipulating showtime entries in XML file
    public Showtime addShowtimeUI(){
        
    }
    
    public Showtime addShowtime(){
        
    }
    
    /* end of Mutators */
    
    private void readMovieListing(){
        // gets the whole list of listing from the XML file.
        file = new XML("/xml/showtime.xml");
        
    }
    
    
    
}
