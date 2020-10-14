package banking.backend;

import java.text.DecimalFormat;

/**
 * This class is used to create the constants that are vital throughout the project.
 * This class contains final static numbers that do not change.
 * @author Michael Zhang, David Testa
 *
 */
public class Constants {
    public final static int NUMBER_OF_MONTHS = 12;
    
    public final static double CHECKING_FEE = 25.0;
    public final static double CHECKING_WAIVED = 1500.0;
    public final static double CHECKING_RATE = 0.0005;
    
    public final static double MONEY_MARKET_FEE = 12.0;
    public final static double MONEY_MARKET_WAIVED = 2500.0;
    public final static double MONEY_MARKET_RATE = 0.0065;
    public final static int MONEY_MARKET_WITHDRAW_LIMIT = 6;
   
    public final static int SAVINGS_WAIVED = 300;
    public final static int ARRAY_INCR = 5;
    public final static double SAVINGS_RATE = 0.0025; 
    public final static double SAVINGS_LOYAL = 0.0035; 
    public final static double SAVINGS_FEE = 5.0;
    
    public final static int JAN = 1;
    public final static int FEB = 2;
    public final static int APR = 4;
    public final static int JUN = 6;
    public final static int SEP = 9;
    public final static int NOV = 11;
    public final static int DEC = 12;
    public final static int MIN_DAY_MONTH = 1;
    public final static int MAX_DAY_MONTH = 31;
    public final static int MIN_MONTHS = 1;
    public final static int MAX_MONTHS = 12;
    public final static int FEB_LEAP = 29;
    public final static int FEB_NORM = 28;
    public final static int QUADRENNIAL = 4;
    public final static int CENTENNIAL = 100;
    public final static int QUATERCENTENNIAL = 400;
    /**
     * This method formats the numbers to make sure it is rounded to the second decimal place.
     * @param num
     * @return numberFormat
     */
    public static String numFormator( double num ) {
        DecimalFormat numberFormat = new DecimalFormat();
        numberFormat.applyPattern( "0.00" );
        return numberFormat.format( num );
    }
}

