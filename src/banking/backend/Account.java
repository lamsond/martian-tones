package banking.backend;

/**
 * This class is used to get the information from the user and store their info into the object called Account
 * This class is needed as it uses the "getter" methods to retrieve vital information that is used throughout the program.
 * This information will be stored in the array for later use.
 * @author Michael Zhang, David Testa
 *
 */
public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateOpen;    
    /**
     * Constructor that is used to set the params.
     * @param holder
     * @param balance
     * @param dateOpen
     */
    public Account( Profile holder, double balance, Date dateOpen ) {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateOpen;
    }   
    /**
     * Get method for balance.
     * @return balance.
     */
    public double getBalance() {
        return balance;
    }
    /**
     * Get method for holder.
     * @return holder.
     */
    public Profile getHolder() {
        return holder;
    }
    /**
     * Get method for date the account was opened.
     * @return dateOpen.
     */
    public Date getDateOpen() {
        return dateOpen;
    }
    /**
     * Decrease the balance by amount.
     * @param amount
     */
    public void debit( double amount ) { 
        this.balance -= amount;
    }
    /**
     * Increase the balance by amount.
     * @param amount
     */
    public void credit( double amount ) { 
        this.balance += amount;
    } 
    /**
     * ToString method for output of the first name last name and date account was opened.
     */
    public String toString() { 
        return "*" + holder.getFname() + " " + holder.getLname() + "* $" 
                    + Constants.numFormator( balance ) + "*" + dateOpen.toString();
    }
    /**
     * Call method monthlyInterest().
     * @return interest
     */
    public abstract double monthlyInterest();
    /**
     * Call method monthlyFee().
     * @return fee
     */
    public abstract double monthlyFee();
    
}
