package CZ2002;

/* this file contains example code */

public enum CineplexList {
    C1 ("PX1801", "Punggol"),
    C2 ("PL8402", "Kovan"),
    C3 ("KF603", "Woodlands");

    private final String CineplexID;
    private final String CineplexName;
    CineplexList(String CineplexID, String CineplexName){
        this.CineplexID = CineplexID;
        this.CineplexName = CineplexName;
    }
    
    public String CineplexID() {
        return CineplexID;
    }
    public String CineplexName() {
        return CineplexName;
    }
}
