package banking.backend;

/**
 * The class is served to interact with the savings aspect of an account.
 * This class is vital as there are actions, fees, and rates that are constants with the savings account.
 * @author Michael Zhang, David Testa
 */
public class Savings extends Account {

    private boolean isLoyal;

    /**
     * Constructor that is used to set the params for the holder balance date open and loyalty .
     * @param holder
     * @param balance
     * @param dateOpen
     * @param isLoyal
     */
    public Savings( Profile holder, double balance,
            Date dateOpen, boolean isLoyal ) {
        super( holder, balance, dateOpen );
        this.isLoyal = isLoyal;
    }
    /**
     * This method is used to perform the annual interest on the savings account.
     * Helper method for monthlyInterest.
     * @return annualInterest
     */
    private double annualInterest() {
        double annualInterest;

        if ( isLoyal == true) {
            annualInterest = Constants.SAVINGS_LOYAL;
        } else {
            annualInterest = Constants.SAVINGS_RATE;
        }
        return annualInterest;
    }
    /**
     * This method is used to perform the monthly interest on the savings account.
     * @return monthlyInterest
     */
    @Override
    public double monthlyInterest() {
        return ( super.getBalance() * (annualInterest() ) )
                / Constants.NUMBER_OF_MONTHS;
    }
    /**
     * This method is used to perform the monthly fee on the savings account.
     * getBalanace from Account and check if it is >= $300
     * @return monthlyFee
     */
    public double monthlyFee() {
        if ( super.getBalance() >= Constants.SAVINGS_WAIVED ) {
            return 0;
        }
        return  Constants.SAVINGS_FEE;
    }
    /**
     * This method is used to output the special savings to an account.
     * This method will display a message to the user if the account qualifies for "isLoyal".
     * @return String
     */
    public String toString() {
        if ( isLoyal ) {
            return "*Savings" + super.toString() + "*special Savings account*";
        } else {
            return "*Savings" + super.toString();
        }
    }
    /**
     * Checks if the account name and type is the same will return true if it is the same otherwise returns false.  
     * @return boolean
     */
    public boolean equals( Object account ) {
        if( account instanceof Savings ) {            
            Account checkAccount = ( ( Account ) account );
            if( checkAccount.getHolder().equals( super.getHolder() ) ) {
                return true;
            }
        }
        return false;
    }
}

