package CZ2002;
/**
 * Cinema class
 * @author calvinlee
 */
public class Cinema {

    private String name;
    //private Seat[] seat;
    private String cinemaType;
    
    /**
     * Constructor
     * @param name 
     */
    public Cinema(String name) {
        this.name = name;
    }
    
    /**
     * Returns the name of the Cinema
     * @return 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Updates the name of the Cinema
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the type of the Cinema
     * @return 
     */
    public String getCinemaType() {
        return this.cinemaType;
    }
    
    /**
     * Updates the type of the Cinema
     * @param cinemaType 
     */
    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }
    
    public void getLayout() {
        // enter code here
    }
}
