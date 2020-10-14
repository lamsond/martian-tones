package banking.backend;

/**
 * This lass is used to to contain the money market.
 * @author Michael Zhang, David Testa
 *
 */
public class MoneyMarket extends Account {
    private int withdrawals;
    
    /**
     * Constructor that is used to set the params.
     * @param holder
     * @param balance
     * @param dateOpen
     */
    public MoneyMarket ( Profile holder, double balance, 
            Date dateOpen ) {
        super( holder, balance, dateOpen );
        withdrawals = 0;
    }
    /**
     * This method keeps track on how many times the user withdrawals from the account. 
     */
    public void addWithdrawals() {
        withdrawals++;
    }
    
    /**
     * This method calculated the monthly interest from the balance.
     * @return monthlyInterst
     */
    @Override
    public double monthlyInterest() {
        return ( super.getBalance() * ( Constants.MONEY_MARKET_RATE ) ) 
                / Constants.NUMBER_OF_MONTHS;
    }
    /**
     * This method calculated the monthly fee from the balance.
     * @return monthlyFee
     */
    @Override
    public double monthlyFee() {
        if( super.getBalance() >= Constants.MONEY_MARKET_WAIVED 
                && this.withdrawals <= Constants.MONEY_MARKET_WITHDRAW_LIMIT ) {
            return 0.0;
        }
        
        return Constants.MONEY_MARKET_FEE;
    }
    /**
     * This method is used to output the information to the user as displayed.
     * @return String
     */
    public String toString() {
        return "*Money Market" + super.toString() + "*" + this.withdrawals + " withdrawals*";
    }
    /**
     * Checks if the account name and type is the same will return true if it is the same otherwise returns false. 
     * @return boolean
     */
    public boolean equals( Object account ) {
        if( account instanceof MoneyMarket ) {            
            Account checkAccount = ( ( Account ) account );
            if( checkAccount.getHolder().equals( super.getHolder() ) ) {
                return true;
            }
        }

        return false;
    }
    
}

