// For users of MOBLIMA to view their transaction history
package CZ2002;

/**
 * 
 * @author calvinlee
 */
public class History{
    
    private XML bookingXML;
    private String userId;
    private String[] history;
    private String[] userHistory;
    private int historySize;
    /**
     * Constructor for History
     */
    public History() {
        
    }
    /**
     * Get userId and returns userHistory array
     * @param userId
     * @return userHistory array
     */
    public String[] getUserHistory(String userId) {
        this.userId = userId;
        historySize = 0;
        int count = 0;
        bookingXML = new XML("booking");
        history = bookingXML.getElement();
        for(int i = 0; i < history.length; i++) {
            if(this.userId.equals(bookingXML.getItemContent(history[i], "userId")));
                historySize++;
        }
        userHistory = new String[historySize];
        for(int i = 0; i < history.length; i++) {
            if(this.userId.equals(bookingXML.getItemContent(history[i], "userId")));
                userHistory[count] = bookingXML.getItemContent(history[i], "TID");
        }
        return userHistory;
    }
}
