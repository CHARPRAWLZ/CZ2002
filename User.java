package CZ2002;

/*
 author: zach
 */
public class User {

    //variables
    private String id, username, password, name, email, mobileNo;

    //constructors
    public User() {
    }

    /**
     *
     * @param username
     * @param password
     * @param name
     * @param email
     * @param mobileNo
     */
    public User(String username, String password, String name, String email, String mobileNo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;

    }

    //accessors
    /**
     * Returns the increment id of the user account
     *
     * @return id
     */
    public String getUserId() {
        return this.id;
    }

    /**
     * Returns the username of the user account
     *
     * @return userName
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the password of the user account
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * returns the name of User instance
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the email address of the User instance
     *
     * @return emailAddress
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * returns the mobile number of the User instance
     *
     * @return mobileNo
     */
    public String getMobileNo() {
        return this.mobileNo;
    }

    //mutators
    /**
     * sets the id of the user account
     *
     * @param id
     */
    public void setUserId(String id) {
        this.id = id;
    }

    /**
     * sets the username of the user account
     *
     * @param user
     */
    public void setUsername(String user) {
        this.username = user;
    }

    /**
     * sets the password of the user account
     *
     * @param pw
     */
    public void setPassword(String pw) {
        this.password = pw;
    }

    /**
     * set the user's name
     *
     * @param myName
     */
    public void setName(String myName) {
        this.name = myName;
    }

    /**
     * set the user's email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set the user's mobile number
     *
     * @param number
     */
    public void setMobileNo(String number) {
        this.mobileNo = number;
    }

    /*
     These methods can be reused later as needed
     */
    /*
     Scanner sc = new Scanner(System.in);
     public Movie movie;
  
     /*public void rateMovie(double rating){
     System.out.println("Please rate the selected movie");
     rating = sc.nextDouble();
     movie.setOverallRating(rating);
     System.out.println("Thank you for rating");
     }
  
     public void reviewMovie(String review){
     System.out.println("Please review the selected movie");
     review = sc.next();
     }
   
     public void bookMovie(){}
     */
}
