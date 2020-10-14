package banking.backend;

/**
 * The class is served to interact with the checking aspect of an account.
 * This class is vital as there are actions, fees, and rates that are constants with the Checking account.
 * @author Michael Zhang, David Testa
 *
 */
public class Checking extends Account {
    private boolean directDeposit;
    /**
     * Constructor used to initialize the params for holder, balance, dateOpen, & directDeposit.
     * @param holder
     * @param balance
     * @param dateOpen
     * @param directDeposit
     */
    public Checking( Profile holder, double balance, 
            Date dateOpen, boolean directDeposit ) {
        super( holder, balance, dateOpen );
        this.directDeposit = directDeposit;
    }  
    /**
     * This method is used to perform the monthly interest on the checking account.
     * @return monthly interest.
     */
    @Override
    public double monthlyInterest() {
        return ( super.getBalance() * ( Constants.CHECKING_RATE ) ) 
                / Constants.NUMBER_OF_MONTHS;
    }
    /**
     * This method is used to perform the monthly fee on the checking account.
     * @return checking fee.
     */
    @Override
    public double monthlyFee() {
        if(super.getBalance() >= Constants.CHECKING_WAIVED || directDeposit) {
            return 0.0;
        }
            
        return Constants.CHECKING_FEE;
    }
    /**
     * This method is used to output the information to the user as displayed.
     * @return String.
     */
    public String toString() {
        if( directDeposit )
            return "*Checking" + super.toString() + "*direct deposit account*";
        
        return "*Checking" + super.toString();
    }
    /**
     * Checks if the account name and type is the same will return true if it is the same otherwise returns false. 
     * @return boolean.
     */
    public boolean equals( Object account ) {
        if( account instanceof Checking ) {            
            Account checkAccount = ( ( Account ) account );
            if( checkAccount.getHolder().equals( super.getHolder() ) ) {
                return true;
            }
        }

        return false;
    }
}
