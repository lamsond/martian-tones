package banking.backend;

/**
 * This class is used to contain the information of the profile of the account.
 * This class will store vital information such as the first name and last name of the account holder.
 * @author Michael Zhang, David Testa
 *
 */
public class Profile {
    private String fname;
    private String lname;
    /**
     * Constructor to initialize fname and lname.
     * @param fname
     * @param lname
     */
    public Profile( String fname, String lname ) {
        this.fname = fname;
        this.lname = lname;
    }
    /**
     * Getter method to retrieve the first name of the account holder.
     * @return fname
     */
    public String getFname() {
        return fname;
    }
    /**
     * Getter method to retrieve the last name of the account holder.
     * @return lname
     */
    public String getLname() {
        return lname;
    }
    /**
     * This method will check if the first name and the last names are the same.
     * Compares two names and compares if they are the same and returns true if not return false.
     * @return boolean
     */
    public boolean equals( Object profile ) {
        if( profile instanceof Profile ) {
            Profile checkProfile = ( Profile ) profile;
            return ( this.fname.equals( checkProfile.getFname() ) 
                     && this.lname.equals( checkProfile.getLname() ) );
        }
        
        return false;
    }
}
