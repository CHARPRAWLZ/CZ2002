package CZ2002;

public class Cineplex {
    private String cineplexID,cineplexName,locationName;

    public void getName(){
        System.out.println(cineplexID + cineplexName + locationName);
    }
    public void setName(String cineplex, String location, String id){
        cineplexName = cineplex;
        cineplexID = id;
        locationName = location;
    }
}
