package CZ2002;
import java.util.ArrayList;
import java.util.Scanner;

public class Cineplex {
    private String cineplexID,cineplexName,locationName;
    private ArrayList<Cinema> cinemaList = new <Cinema>ArrayList();
    
    private enum locations {
        Punggol, Sengkang, Buangkok, Hougang
    }
    
    public Cineplex(String cineplexID, String cineplexName, locations loc){
        for(int i = 0; i < 3; i++) {
            Cinema cinema = new Cinema(cineplexID.concat("-" + Integer.toString(i+1)));
            this.cinemaList.add(cinema);
        }
        this.cineplexID = cineplexID;
        this.cineplexName = cineplexName;
        this.locationName = loc.toString();
    }

    public void getName(){
        System.out.println(cineplexID + cineplexName + locationName);
    }
    public void setName(String cineplex, String location, String id){
        cineplexName = cineplex;
        cineplexID = id;
        locationName = location;
    }
    
    public static void main(String[] args) {
        /*int i = 1;
        Scanner sc = new Scanner(System.in);
        for (CineplexList e : CineplexList.values()){
            System.out.println(i + " : " + e.CineplexID() + " : " + e.CineplexName());
        }*/
        locations e;
        System.out.println(CineplexList.values()[1].CineplexID() + " : " + CineplexList.values()[1].CineplexName());
    }
}
