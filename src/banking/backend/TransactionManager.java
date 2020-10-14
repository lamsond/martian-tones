package banking.backend;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is used the manage the transactions of the accounts.
 * This class will be about to read what the inputs are performed by the user and call the appropriate methods to execute the given task.
 * @author Michael Zhang, David Testa
 *
 */
public class TransactionManager {
    
    private Scanner input;
    private AccountDatabase database;
    
    /**
     * This method is used to run the transaction processing. 
     */
    public void run() {
        System.out.println( "Transaction Processing starts....." );
        database = new AccountDatabase();
        input = new Scanner( System.in );
        String command = input.nextLine();
        boolean status = commandInput( command );
        while( status == true ) {
            command = input.nextLine();
            status = commandInput( command );
        }
    }
    /**
     * This method is used as the command input manager.
     * This method is vital as it will be able to read the input from the user.
     * @param command
     * @return boolean
     */
    private boolean commandInput( String command ) {
        StringTokenizer tokenizer = new StringTokenizer( command );
        if( tokenizer.countTokens() == 6 
                || tokenizer.countTokens() == 5 ) {
            openAccount( tokenizer );
        }else if( tokenizer.countTokens() == 3 ) {
            closeAccount( tokenizer );
        }else if( tokenizer.countTokens() == 4 ) {
            deposit_Withdraw( tokenizer );
        }else if( tokenizer.countTokens() == 1 ) {
            if( ( new StringTokenizer( command ) )
                    .nextToken().length() == 2 ) {
                print( tokenizer );
            }else if( ( new StringTokenizer( command ) )
                    .nextToken().length() == 1 ) {
                String token = tokenizer.nextToken();
                if( token.charAt( 0 ) == 'Q' ) {
                    System.out.println( "Transaction processing completed." );
                    return false;
                }
                formatError( token );
            }
        }       
        return true;
    }   
    /**
     * This method is used to take in the 'O' input and open an account.
     * This will allow the user to interact with the program. 
     * @param tokenizer
     */
    private void openAccount( StringTokenizer tokenizer ) {
        String token = tokenizer.nextToken();
        if( token.length() == 2 ) {
            if( token.charAt( 0 ) == 'O' ) {
                String fname = tokenizer.nextToken();
                String lname = tokenizer.nextToken();
                Profile profile = createProfile( fname, lname );
                String bal = tokenizer.nextToken();
                double balance = getBalance( bal );
                String date_S = tokenizer.nextToken();
                Date date = createDate( date_S );
                if( date == null ) {
                    return;
                }
                
                if( profile != null && balance >= 0 && date != null ) {
                    if( token.charAt(1) == 'C' ) { 
                        token = tokenizer.nextToken();
                        if( "false".equals( token.toLowerCase() ) ) {
                            database.add( new Checking( profile, balance, date, false ) );
                        }else if( "true".equals( token.toLowerCase() ) ) {
                            database.add( new Checking( profile, balance, date, true ) );
                        }else {
                            mismatchError();
                        }
                        return;
                    }else if( token.charAt( 1 ) == 'S' ) {
                        token = tokenizer.nextToken();
                        if( "false".equals( token.toLowerCase() ) ) {
                            database.add( new Savings( profile, balance, date, false ) );
                        }else if( "true".equals( token.toLowerCase() ) ) {
                            database.add( new Savings( profile, balance, date, true ) );
                        }else {
                            mismatchError();
                        }
                        return;
                    }else if( token.charAt( 1 ) == 'M' ) {
                        database.add( new MoneyMarket( profile, balance, date ) );   
                        return;
                    }
                }else {
                    if( token.charAt( 1 ) == 'C' ||  token.charAt(1) == 'S' ||  token.charAt(1) == 'M' ) {
                        mismatchError();
                    }else {
                        formatError( token );
                    }
                    return;
                }
            }
        }
        formatError( token );
    }
    /**
     * This method is used to take in the 'C' input and close an account.
     * This will allow the user to interact with the program.
     * @param tokenizer
     */
    private void closeAccount( StringTokenizer tokenizer ) {
        String token = tokenizer.nextToken();
        if( token.length() == 2 ) {
            if( token.charAt( 0 ) != 'C' ) {
                formatError( token );
                return;
            }
            
            if( token.charAt( 0 ) == 'C' ) {
                if( token.charAt( 1 ) == 'C' ) {
                    database.remove( new Checking( createProfile( tokenizer.nextToken(), tokenizer.nextToken() )
                            , 0, null, false ) );
                    return;
                }else if( token.charAt( 1 ) == 'S' ) {
                    database.remove( new Savings( createProfile( tokenizer.nextToken(), tokenizer.nextToken() )
                            , 0, null, false ) );
                    return;
                }else if( token.charAt( 1 ) == 'M' ) {
                    database.remove( new MoneyMarket( createProfile( tokenizer.nextToken(), tokenizer.nextToken() )
                            , 0, null) );
                    return;
                }
            }
        }
        formatError( token );
    }
    /**
     * This method is used to take in the 'W' input and withdraw from an account.
     * This will allow the user to interact with the program.
     * @param tokenizer
     */
    private void deposit_Withdraw( StringTokenizer tokenizer ) {
        String token = tokenizer.nextToken();
        if( token.length() == 2 ) {
            if( token.charAt( 0 ) != 'W' && token.charAt( 0 ) != 'D') {
                formatError( token );
                return;
            }
            String fname = tokenizer.nextToken();
            String lname = tokenizer.nextToken();
            Profile profile = createProfile( fname, lname );
            String amount_S = tokenizer.nextToken();
            double amount = getBalance( amount_S );
            if( amount >= 0 ) {
                if( token.charAt( 0 ) == 'W' ) {
                    if( token.charAt( 1 ) == 'C' ) {
                        database.withdrawal( new Checking( profile, 0, null, false ) , amount );
                        return;
                    }else if( token.charAt( 1 ) == 'S' ) {
                        database.withdrawal( new Savings( profile, 0, null, false ) , amount );
                        return;
                    }else if( token.charAt( 1 ) == 'M' ) {
                        database.withdrawal( new MoneyMarket( profile, 0, null ) , amount );
                        return;
                    }
                }else if( token.charAt( 0 ) == 'D' ) {
                    if( token.charAt( 1 ) == 'C' ) {
                        database.deposit( new Checking( profile, 0, null, false ) , amount );
                        return;
                    }else if( token.charAt( 1 ) == 'S' ) {
                        database.deposit( new Savings( profile, 0, null, false ) , amount );
                        return;
                    }else if( token.charAt( 1 ) == 'M' ) {
                        database.deposit( new MoneyMarket( profile, 0, null ) , amount );
                        return;
                    }
                }
            }else {
                if( ( token.charAt( 0 ) == 'W' ||  token.charAt(0) == 'D' ) 
                        && ( token.charAt( 1 ) == 'C' ||  token.charAt( 1 ) == 'S' ||  token.charAt( 1 ) == 'M' ) ) {
                    mismatchError();
                }else {
                    formatError( token );
                }
                return;
            }
        }
        formatError( token );
    }
    /**
     * This method is used to take in the 'P' input and print the account.
     * This will allow the user to interact with the program.
     * @param tokenizer
     */
    private void print( StringTokenizer tokenizer ) {
        String token = tokenizer.nextToken();
        if( token.length() == 2 ) {
            if( token.charAt( 0 ) == 'P' ) {
                if( token.charAt( 1 ) == 'A' ) {
                    database.printAccounts();
                    return;
                }else if( token.charAt( 1 ) == 'D' ) {
                    database.printByDateOpen();
                    return;
                }else if( token.charAt( 1 ) == 'N' ) {
                    database.printByLastName();
                    return;
                }
            }
        }
        formatError( token );
    }    
    /**
     * The method is used to get the balance from an account.
     * This method will allow the user to interact with the program and return the balance.
     * @param balance
     * @return balance
     */
    private double getBalance( String balance ) {
        double bal;
        try {
            bal = Double.parseDouble( balance );
        }catch( Exception e ) {
            bal = -1;
        }
        return bal;
    }
    /**
     * This method is used to create a date in a human readable format.
     * This will allow the user to read a displayed date of an account.
     * @param date_Whole
     * @return Date
     */
    private Date createDate( String date_Whole ) {
        StringTokenizer token = new StringTokenizer( date_Whole, "/" );
        if( token.countTokens() == 3 ) {
            int day;
            int date;
            int year;
            try {
                day = Integer.parseInt( token.nextToken() );
                date = Integer.parseInt( token.nextToken() );
                year = Integer.parseInt( token.nextToken() );
            }catch( Exception e ) {
                day = -1;
                date = -1;
                year = -1;
            }
            Date date_Final = new Date( day, date, year );
            if( date_Final.isValid() ) {
                return date_Final;
            }else {
                System.out.println( date_Whole + " is not a valid date!");
                return null;
            }
        }
        return null;        
    }
    /**
     * This method is used to create a profile.
     * This method is vital as it interacts with accounts objects to take in the first and last names.
     * @param fname
     * @param lname
     * @return Profile
     */
    private Profile createProfile( String fname, String lname ) {
        Profile profile = new Profile( fname, lname );
        return profile;
    }
    /**
     * This method is used to output an error message when an invalid format is inputed. 
     * @param token
     */
    private void formatError( String token ) {
        System.out.println( "Command '" + token + "' not supported!" );
    }
    /**
     * This method is used to output an error message when an invalid command is inputed. 
     */
    private void mismatchError() {
        System.out.println( "Input data type mismatch." );
    }
    
    
}

