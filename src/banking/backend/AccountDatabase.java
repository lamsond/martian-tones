package banking.backend;


/**
 * This class is our AccountDatabase. This class is used to implement all of the action you can perform on the account.
 * This class is used to serve as the "work" that is going on in the background.
 * This work involves add, remove, find, grow. etc. These are all actions that the user can perform on the accounts.
 * @author Michael Zhang, David Testa
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;   
    /**
     * Constructor which is used to initialize or array account.
     */
    public AccountDatabase() {
        accounts = new Account[ 5 ];
        size = 0;
    }   
    /**
     * This method is used to traverse through the array to find the desired location.
     * This method will return the index of the position which will be used for add, remove, and grow methods.
     * @param account
     * @return index.
     */
    private int find( Account account ) {
        if ( account == null ) {
            return -1;         
        }
        for ( int i=0; i < size; i++ ) {
            if ( account.equals( accounts[ i ] ) ) {
                return i;
            }
        }
        return -1;
    }  
    /**
     * This method is our grow method.
     * This method is used to increase the size of the array that we need for the accounts.
     * The method is called when the array is filled and we must increase to include another account object.
     */
    private void grow() {
        int newLen = accounts.length + Constants.ARRAY_INCR;
        
        Account[] tempAccounts = new Account[newLen]; 
        
        for ( int i = 0; i < accounts.length; i++ ) {
            tempAccounts[ i ] = accounts[ i ];
        }
        accounts = tempAccounts;
    }   
    /**
     * This method is used to add an account to the array.
     * This method is vital as the user can perform the command to add their account to the array.
     * @param account
     * @return boolean if account is found.
     */
    public boolean add( Account account ) { 
        if( find( account ) >= 0 ) {
            System.out.println( "Account is already in the database." );
            return false;
        }       
        if ( size == accounts.length ) {
            grow();
        }
        
        accounts[ size ] = account;
        size++;
        System.out.println( "Account opened and added to the database." );
        return true;
    }
    /**
     * This method is used to remove the account from the array.
     * This method is vital as the user can perform the command to remove their account from the array.
     * @param account
     * @return boolean if account is removed.
     */
    public boolean remove( Account account ) {
        int itemPosition = find( account );
 
        if ( itemPosition != -1 ) {
            accounts[ itemPosition ] = null;
            size --;
            accounts[ itemPosition ] = accounts[ size ];
            accounts[ size ] = null;
            System.out.println( "Account closed and removed from the database." );
            return true;
        }
        else {
            System.out.println( "Account does not exist." );
        }
        return false;
    }    
    /**
     * This method is used to deposit money to an existing account.
     * This method will Return 0: withdrawal successful, 1: insufficient funds, -1 account doesn't exists.
     * @param account
     * @param amount
     * @return index.
     */
    public int deposit( Account account, double amount ) {
        int itemPosition = find( account );
        
        if( itemPosition != -1 ) {
            accounts[ itemPosition ].credit( amount );
            System.out.println( Constants.numFormator( amount ) + " deposited to account." );
            return 1;
            
        }
        System.out.println( "Account does not exist." );
        return -1;
    }
    /**
     * This method is used to withdrawal money from an existing account.
     * This method allows the user to perform a command to withdrawal money.
     * @param account
     * @param amount
     * @return index.
     */
    public int withdrawal( Account account, double amount ) {
        int itemPosition = find( account );
        
        if( itemPosition != -1 ) {
            if( ( accounts[ itemPosition ].getBalance() - amount ) >= 0 ) {
                if( account instanceof MoneyMarket ) {
                    ( ( MoneyMarket ) accounts[ itemPosition ] ).addWithdrawals();
                }
                accounts[ itemPosition ].debit( amount );
                System.out.println( Constants.numFormator( amount ) + " withdrawn from account." );
                return 0;
            }else {
                System.out.println( "Insufficient funds." );
                
                return 1;
            }
            
        }
        System.out.println( "Account does not exit." );
        return -1;
    }
    /**
     * This method will take in array.
     * Outer loop to traverse through the array searching for date.
     * Outer starts at 1 counts up.
     * Inner loop counts down from i-1 to 0.
     * Sort by date.
     */
    private void sortByDateOpen() {
        int n = size; 
        for ( int i = 1; i < n; ++i ) { 
            Account key = accounts[ i ]; 
            int j = i - 1; 
            while ( j >= 0 && accounts[ j ].getDateOpen().compareTo( key.getDateOpen() ) > 0 ) { 
                accounts[ j + 1 ] = accounts[ j ]; 
                j = j - 1; 
            } 
            accounts[ j + 1 ] = key; 
        } 
    }    
    /**
     * This method will take in array.
     * Outer loop to traverse through the array searching for last name.
     * Outer starts at 1 counts up.
     * Inner loop counts down from i-1 to 0.
     * Sort by last name.
     */
    private void sortByLastName() {
        int n = size; 
        for ( int i = 1; i < n; ++i ) { 
            Account key = accounts[ i ]; 
            int j = i - 1; 
            while ( j >= 0 && accounts[ j ].getHolder().getLname()
                    .compareTo( key.getHolder().getLname() ) > 0 ) { 
                accounts[ j + 1 ] = accounts[ j ]; 
                j = j - 1; 
            } 
            accounts[ j + 1 ] = key; 
        } 
    } 
    /**
     * This method is used to print the date opened an account.
     */
    public void printByDateOpen() {
        if( size == 0 ) {
            System.out.println( "Database is empty." );
            return;
        }
        
        sortByDateOpen();
        System.out.println();
        System.out.println( "--Printing statements by date opened--" );
        for( int i = 0; i < size; i++ ) {
            System.out.println();
            System.out.println( accounts[ i ] );
            System.out.println( "-interest: $ " 
                            + Constants.numFormator( accounts[ i ].monthlyInterest() ) );
            System.out.println( "-fee: $ " 
                            + Constants.numFormator( accounts[ i ].monthlyFee() ) );
            
            accounts[ i ].credit( accounts[ i ].monthlyInterest() );
            accounts[ i ].debit( accounts[ i ].monthlyFee() );
            
            System.out.println( "-new balance: $ " 
                            + Constants.numFormator( accounts[ i ].getBalance() ) );
        }
        System.out.println( "--end of printing--" );
        
    }
    /**
     * This method is used to print the last name of the account holder. 
     */
    public void printByLastName() {
        if( size == 0 ) {
            System.out.println( "Database is empty." );
            return;
        }
        
        sortByLastName();
        System.out.println();
        System.out.println( "--Printing statements by last name--" );
        for( int i = 0; i < size; i++ ) {
            System.out.println();
            System.out.println( accounts[ i ] );
            System.out.println( "-interest: $ " 
                            + Constants.numFormator( accounts[ i ].monthlyInterest() ) );
            System.out.println( "-fee: $ " 
                            + Constants.numFormator( accounts[ i ].monthlyFee() ) );
            
            accounts[ i ].credit( accounts[ i ].monthlyInterest() );
            accounts[ i ].debit( accounts[ i ].monthlyFee() );

            System.out.println( "-new balance: $ " + Constants.numFormator( accounts[ i ].getBalance() ) );
        }
        System.out.println( "--end of printing--" );
    }
    /**
     * This method will print the accounts when called. 
     */
    public void printAccounts() {
        if( size == 0 ) {
            System.out.println( "Database is empty." );
            return;
        }
        System.out.println( "--Listing accounts in the database--" );
        for( int i = 0; i < size; i++ ) {
            System.out.println( accounts[ i ] );
        }
        System.out.println( "--end of listing--" );
    }
}
