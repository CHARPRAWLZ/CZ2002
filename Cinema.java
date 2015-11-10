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
     * Constructor for Cinema
     * @param name name of the Cinema
     */
    public Cinema(String name) {
        this.name = name;
    }
    
    /**
     * Returns the name of the Cinema
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Updates the name of the Cinema
     * @param name name of the Cinema
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the type of the Cinema
     * @return cinemaType
     */
    public String getCinemaType() {
        return this.cinemaType;
    }
    
    /**
     * Updates the type of the Cinema
     * @param cinemaType type of the Cinema
     */
    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }
    
    public void getLayout() {
        // enter code here
    }
}
