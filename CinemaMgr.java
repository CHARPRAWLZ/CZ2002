package CZ2002;
/**
 * This class manages the cinema allocated 
 * according to the cineplex stated
 * @author Zach
 */
public class CinemaMgr {

    private Cineplex[] cineplex = new Cineplex[3];
    
    /**
     * Creates a list of cinemas available
     */
    public CinemaMgr(){
        cineplex[0] = new Cineplex("GV", "Golden Village", "Vivo City", 6);
        cineplex[1] = new Cineplex("CTH", "Cathay", "Orchard", 7);
        cineplex[2] = new Cineplex("SHW", "SHAW", "Dhoby Ghaut", 6);
    }
    /**
     * shows the list of Cineplexes
     * @return cineplexName
     */
    public String[] getCineplexList() {
        String[] cineplexName = new String[3];
        for (int i = 0; i < 3; i++){
            cineplexName[i] = cineplex[i].getName();
        }
        return cineplexName;
    }
    /**
     * shows the Cineplex chosen
     * @param choice
     * @return 
     */
    public Cineplex getCineplex(int choice){
        return cineplex[choice];
    }
    /**
     * initialise the list of cinemas in a cineplex
     * @param choice
     * @return cinema
     */
    public Cinema[] getCinemaList(int choice){
        int size = cineplex[choice].getNumOfCinema();
        Cinema[] cinema = new Cinema[3];
        for (int i = 0; i < size; i++){
            cinema[i] = cineplex[choice].getCinemaList().get(i);
        }
        return cinema;
    }
    /**
     * shows the cinemas available in a cineplex
     * @param cineplex
     * @param cinemaChoice
     * @return 
     */
    public Cinema getCinema(int cineplex, int cinemaChoice){
        return getCinemaList(cineplex)[cinemaChoice];
    }
    /**
     * finds a cineplex using the cineplexId
     * @param cineplexID
     * @return 
     */
    public Cineplex findCineplex(String cineplexID){
        for (Cineplex c : cineplex){
            if (c.getID().equals(cineplexID)){
                return c;
            }
        }
        return null;
    }
    
}
