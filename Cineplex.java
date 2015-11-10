package CZ2002;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cineplex class
 * @author calvinlee
 */
public class Cineplex {
    private final String cineplexID,cineplexName,locationName;
    private ArrayList<Cinema> cinemaList = new <Cinema>ArrayList();
    /*
    private enum locations {
        Punggol, Sengkang, Buangkok, Hougang
    }
    
    public Cineplex(String cineplexID, String cineplexName, locations loc, int cinemaNum){
        for(int i = 0; i < cinemaNum; i++) {
            Cinema cinema = new Cinema(cineplexID.concat("-" + Integer.toString(i+1)));
            this.cinemaList.add(cinema);
        }
        this.cineplexID = cineplexID;
        this.cineplexName = cineplexName;
        this.locationName = loc.toString();
    }*/

    // non-enum version
    /**
     * Constructor
     * @param id
     * @param name
     * @param location
     * @param cinemaNum 
     */
    public Cineplex(String id, String name, String location, int cinemaNum){
        this.cineplexID = id;
        this.cineplexName = name;
        this.locationName = location;
        for(int i = 0; i < cinemaNum; i++) {
            Cinema cinema = new Cinema(this.cineplexID.concat("-" + Integer.toString(i+1)));
            this.cinemaList.add(cinema);
        }
    }
    
    // get and set methods()
    /**
     * Returns the ID of the Cineplex
     * @return 
     */
    public String getID() {
        return this.cineplexID;
    }
    
    /**
     * Returns the name of the Cineplex
     * @return 
     */
    public String getName(){
        return this.cineplexName;
    }
    
    /**
     * Returns the location of the Cineplex
     * @return 
     */
    public String getLocation(){
        return this.locationName;
    }
    
    /**
     * Returns the cinemaList of the Cineplex
     * @return 
     */
    public ArrayList<Cinema> getCinemaList() {
        return this.cinemaList;
    }
    
    /**
     * Updates the cinemaList of the CIneplex
     * @param cinemaList 
     */
    public void setCinemaList(ArrayList<Cinema> cinemaList) {
        this.cinemaList = cinemaList;
    }
    /*
    public void setName(String cineplex, String location, String id){
        cineplexName = cineplex;
        cineplexID = id;
        locationName = location;
    }
    */
    /**
     * Returns the number of cinema of the Cineplex
     * @return 
     */
    public int getNumOfCinema() {
        return cinemaList.size();
    }
    
    // toString method
    /**
     * 
     * @return 
     */
    public String toString() {
        return cineplexID + cineplexName + locationName;
    }
    /*
    public static void main(String[] args) {
        String id, name, loc;
        ArrayList<Cinema> arrayList;
        int num;
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter CineplexID: ");
        id = scn.next();
        System.out.print("Enter Cineplex Name:");
        name = scn.next();
        System.out.print("Enter location");
        loc = scn.next();
        //loc = scn.next();
        System.out.print("Enter number of Cinema");
        num = scn.nextInt();
        Cineplex cineplex = new Cineplex(id, name, loc, num);
        System.out.println("CineplexID: " + cineplex.cineplexID);
        System.out.println("Cineplex Name: " + cineplex.cineplexName);
        System.out.println("Cineplex Location: " + cineplex.locationName);
        System.out.println("Num of Cinema: " + cineplex.getNumOfCinema());
        arrayList = cineplex.getCinemaList();
        for(int i = 0; i < arrayList.size(); i++) {
            System.out.println("Cinema " + (i+1) + " name: " + arrayList.get(i).getName());
        }
        cineplex.getName();
    }*/
}
