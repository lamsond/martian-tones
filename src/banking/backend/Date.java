package banking.backend;


/**
 * This class is used to contain the date information of the accounts.
 * This class will be used for checking the date added and created to the array.
 * This class will be used throughout the database project.
 * @author Michael Zhang, David Testa
 *
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    /**
     * Constructor the initialize params month, day, year.
     * @param month
     * @param day
     * @param year
     */
    public Date( int month, int day, int year ) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    /**
     * Method used to compare dates.
     * @return int
     */
    @Override
    public int compareTo( Date date ) {
        if( this.year == date.year && this.month == date.month
                && this.day == date.day ) {
            return 0;
        }else { 
            if( ( this.year - date.year ) < 0 ) {
                return -1;
            } else if( ( this.year - date.year ) == 0) {
                if( ( this.month - date.month ) < 0 ) {
                    return -1;
                } else if( ( this.month - date.month ) == 0 ) { 
                    if( ( this.day - date.day ) < 0 ) {
                        return -1;
                    }
                }
            }
            return 1;
        }
    }
    /**
     * ToString method to display month, day, year.
     * @return String
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }
    /**
     * Method to check if the date is valid.
     * @return boolean
     */
    public boolean isValid() {
        if( this.month < Constants.MIN_MONTHS || this.month > Constants.MAX_MONTHS 
                || this.day < Constants.MIN_DAY_MONTH || this.day > Constants.MAX_DAY_MONTH 
                || this.year < 0 || this.day < 0 || this.month < 0) {
            return false;
        }
        
        if( this.month == Constants.FEB ) {
            if( isLeap( this.year) ) {
                return ( this.day <= Constants.FEB_LEAP );
            }else {
                return ( this.day <= Constants.FEB_NORM );
            }
        }
        
        if( this.month == Constants.APR ||  this.month == Constants.JUN 
                ||  this.month == Constants.SEP ||  this.month == Constants.NOV ) {
            return ( this.day <= ( Constants.MAX_DAY_MONTH -1 ) );
        }
        
        return true;
    }
    /**
     * Method used to check if leap.
     * @param year
     * @return boolean
     */
    private boolean isLeap( int year ) {
        if( ( this.year % Constants.QUADRENNIAL == 0 && this.year % Constants.CENTENNIAL != 0 ) 
                || year % Constants.QUATERCENTENNIAL == 0 ) {
            return true;
        }else {
            return false;
        }

    }   
}
