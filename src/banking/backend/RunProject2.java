package banking.backend;

/**
 * This class is used to run the project.
 * This class is vital as it is the main() that will call the methods to perform/run the project.
 * @author Michael Zhang, David Testa
 *
 */
public class RunProject2 {
    public static void main( String[] args ) {
        new TransactionManager().run();
    }
}
