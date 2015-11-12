package CZ2002;

public class CinemaMgr {

    Cineplex[] cineplex = new Cineplex[3];
    
    public CinemaMgr(){
        cineplex[0] = new Cineplex("GV", "Golden Village", "Vivo City", 6);
        cineplex[1] = new Cineplex("CTH", "Cathay", "Orchard", 7);
        cineplex[2] = new Cineplex("SHW", "SHAW", "Dhoby Ghaut", 6);
    }
    
    public String[] getCineplexList() {
        String[] cineplexName = new String[3];
        for (int i = 0; i < 3; i++){
            cineplexName[i] = cineplex[i].getName();
        }
        return cineplexName;
    }
    
    public Cineplex getCineplex(int choice){
        return cineplex[choice];
    }
    
    public Cinema[] getCinemaList(int choice){
        int size = cineplex[choice].getNumOfCinema();
        Cinema[] cinema = new Cinema[3];
        for (int i = 0; i < size; i++){
            cinema[i] = cineplex[choice].getCinemaList().get(i);
        }
        return cinema;
    }
    
    public Cinema getCinema(int cineplex, int cinemaChoice){
        return getCinemaList(cineplex)[cinemaChoice];
    }
    
    public Cineplex findCineplex(String cineplexID){
        for (Cineplex c : cineplex){
            if (c.getID().equals(cineplexID)){
                return c;
            }
        }
        return null;
    }
    
}
