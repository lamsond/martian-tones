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
class CheckingTest {

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
     * Test method for {@link transactionManagementProject.Checking#monthlyInterest()}.
     */
    @Test
    void testMonthlyInterest() {
        Checking mm1 = new Checking(null, 0, null, false); // 0 balance
        assertEquals(0.0, mm1.monthlyInterest());
        Checking mm2 = new Checking(null, -1.0, null, false); // -1 balance
        assertEquals(-4.1666666666666665E-5, mm2.monthlyInterest());
        Checking mm3 = new Checking(null, 1.0, null, false); // 1 balance
        assertEquals(4.1666666666666665E-5, mm3.monthlyInterest());
        
        Checking mm4 = new Checking(null, -123.0, null, false); // -123 balance
        assertEquals(-0.005125, mm4.monthlyInterest());
        Checking mm5 = new Checking(null, 123.0, null, false); // 123 balance
        assertEquals(0.005125, mm5.monthlyInterest());
    }

    /**
     * Test method for {@link transactionManagementProject.Checking#monthlyFee()}.
     */
    @Test
    void testMonthlyFee() {
        Checking mm1 = new Checking(null, 0, null, false); // 0 balance
        assertEquals(25.0, mm1.monthlyFee());
        Checking mm2 = new Checking(null, -1, null, false); // -1 balance
        assertEquals(25.0, mm2.monthlyFee());
        Checking mm3 = new Checking(null, 1, null, false); // 1 balance
        assertEquals(25.0, mm3.monthlyFee());
        Checking mm4 = new Checking(null, 1499, null, false); // 1499 balance (edge case)
        assertEquals(25.0, mm4.monthlyFee());
        Checking mm5 = new Checking(null, 1500, null, false); // 1500 balance (edge case)
        assertEquals(0.0, mm5.monthlyFee());
        Checking mm6 = new Checking(null, 1501, null, false); // 1501 balance (edge case)
        assertEquals(0.0, mm6.monthlyFee());
        
        Checking mm7 = new Checking(null, 0, null, false); // direct deposit false
        assertEquals(25.0, mm7.monthlyFee());
        Checking mm8 = new Checking(null, 0, null, true); // direct deposit true
        assertEquals(0.0, mm8.monthlyFee());
    }

}

