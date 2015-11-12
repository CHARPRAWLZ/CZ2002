package CZ2002;
import java.util.ArrayList;

public class ShowTimeMgr {
    
    private XML xml = new XML("showtime");
    private ArrayList <Showtime> showtimes = new <Showtime> ArrayList();
    private MovieListing movieList;
    private CinemaMgr cineplex = new CinemaMgr();
    
    /* Constructors */
    public ShowTimeMgr(MovieListing movieList){
        this.movieList = movieList;
        generateList(); // Generate showtime listing from XML file
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
    
    public void 
    
    /* end of Mutators */
    
    private void generateList(){
        
        
        
    }
    
}
