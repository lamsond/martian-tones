package banking.backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Michael Zhang, David Testa
 *
 */
class MoneyMarketTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test method for {@link transactionManagementProject.MoneyMarket#monthlyInterest()}.
     */
    @Test
    void testMonthlyInterest() {
        MoneyMarket mm1 = new MoneyMarket(null, 0, null); // 0 balance
        assertEquals(0.0, mm1.monthlyInterest());
        MoneyMarket mm2 = new MoneyMarket(null, -1.0, null); // -1 balance
        assertEquals(-5.416666666666666E-4, mm2.monthlyInterest()); 
        MoneyMarket mm3 = new MoneyMarket(null, 1.0, null); // 1 balance
        assertEquals(5.416666666666666E-4, mm3.monthlyInterest());  
        
        MoneyMarket mm4 = new MoneyMarket(null, -123.0, null); // -123 balance
        assertEquals(-0.066625, mm4.monthlyInterest());
        MoneyMarket mm5 = new MoneyMarket(null, 123.0, null); // 123 balance
        assertEquals(0.066625, mm5.monthlyInterest());
        
    }

    /**
     * Test method for {@link transactionManagementProject.MoneyMarket#monthlyFee()}.
     */
    @Test
    void testMonthlyFee() {
        MoneyMarket mm1 = new MoneyMarket(null, 0, null); // 0 balance
        assertEquals(12.0, mm1.monthlyFee());
        MoneyMarket mm2 = new MoneyMarket(null, -1, null); // -1 balance
        assertEquals(12.0, mm2.monthlyFee());
        MoneyMarket mm3 = new MoneyMarket(null, 1, null); // 1 balance
        assertEquals(12.0, mm3.monthlyFee());
        MoneyMarket mm4 = new MoneyMarket(null, 2499, null); // 2499 balance (edge case)
        assertEquals(12.0, mm4.monthlyFee());
        MoneyMarket mm5 = new MoneyMarket(null, 2500, null); // 2500 balance (edge case)
        assertEquals(0.0, mm5.monthlyFee());
        MoneyMarket mm6 = new MoneyMarket(null, 2501, null); // 2501 balance (edge case)
        assertEquals(0.0, mm6.monthlyFee());
        
        MoneyMarket mm7 = new MoneyMarket(null, 2500, null); // 6 withdrawals (edge case)
        mm7.addWithdrawals();
        mm7.addWithdrawals();
        mm7.addWithdrawals();
        mm7.addWithdrawals();
        mm7.addWithdrawals();
        mm7.addWithdrawals();
        assertEquals(0.0, mm7.monthlyFee());
        MoneyMarket mm8 = new MoneyMarket(null, 2500, null); // 7 withdrawals (edge case)
        mm8.addWithdrawals();
        mm8.addWithdrawals();
        mm8.addWithdrawals();
        mm8.addWithdrawals();
        mm8.addWithdrawals();
        mm8.addWithdrawals();
        mm8.addWithdrawals();
        assertEquals(12.0, mm8.monthlyFee());
        
    }

}

