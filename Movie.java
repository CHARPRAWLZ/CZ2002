package CZ2002;

public class Movie {
    private movieBank bank;
    private String title, synopsis, director, cast, type;
    private double overallRating;
    private String movieID;
    
    // Constructors
    
    Movie(String title, String movieID){
        boolean flag = true;
        for (int i = 0; i < bank.listingSize; i++){
            if (title == bank.movieTitle[i] || movieID == bank.movieID[i]){
                flag = false;
                break;
            }
        }
        if (flag){
            this.title = title;
            this.movieID = movieID;
            
            //code to insert new entry to external file
        }
    }
    
    
    // Accessors
    
    public String getTitle(){
        return title;
    }
    
    public String getSynopsis(){
        return synopsis;
    }
    
    public String getDirector(){
        return director;
    }
    
    public String getCast(){
        return cast;
    }
    
    public String getType(){
        return type;
    }
    
    // end of Accessors
    
}

class movieBank{
    // load movie listings to use to compare for entry clashes
    String[] movieTitle, movieID;
    int listingSize;
    
    // code to obtain data from external file
}