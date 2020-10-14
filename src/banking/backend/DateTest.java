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
class DateTest {

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
     * Test method for {@link transactionManagementProject.Date#isValid()}.
     */
    @Test
    void testIsValid() {
        
        // Leap year dates
        Date d1 = new Date(2, 29, 4); // Quadrennial
        assertTrue(d1.isValid());
        Date d2 = new Date(2, 29, 100); // Centennial
        assertFalse(d2.isValid());
        Date d3 = new Date(2, 29, 400); // QUATERCENTENNIAL
        assertTrue(d3.isValid());
        
        Date d4 = new Date(2, 30, 4); // Quadrennial
        assertFalse(d4.isValid());
        Date d5 = new Date(2, 30, 100); // Centennial
        assertFalse(d5.isValid());
        Date d6 = new Date(2, 30, 400); // QUATERCENTENNIAL
        assertFalse(d6.isValid());
        
        // Negative dates
        Date d7 = new Date(-3, 12, 2000); // Negative Month
        assertFalse(d7.isValid());
        Date d8 = new Date(3, -12, 2000); // Negative Day
        assertFalse(d8.isValid());
        Date d9 = new Date(3, 12, -2000); // Negative Year
        assertFalse(d9.isValid());
        
        // Valid dates
        Date d10 = new Date(1, 31, 2000); // Jan
        assertTrue(d10.isValid());
        Date d11 = new Date(3, 31, 2000); // Mar
        assertTrue(d11.isValid());
        Date d12 = new Date(4, 30, 2000); // Apr
        assertTrue(d12.isValid());
        Date d13 = new Date(5, 31, 2000); // May
        assertTrue(d13.isValid());
        Date d14 = new Date(6, 30, 2000); // Jun
        assertTrue(d14.isValid());
        Date d15 = new Date(7, 31, 2000); // Jul
        assertTrue(d15.isValid());
        Date d16 = new Date(8, 31, 2000); // Aug
        assertTrue(d16.isValid());
        Date d17 = new Date(9, 30, 2000); // Sep
        assertTrue(d17.isValid());
        Date d18 = new Date(10, 31, 2000); // Oct
        assertTrue(d18.isValid());
        Date d19 = new Date(11, 30, 2000); // Nov
        assertTrue(d19.isValid());
        Date d20 = new Date(12, 31, 2000); // Dec
        assertTrue(d20.isValid());
        
        // Invalid dates over day limit
        Date d21 = new Date(1, 32, 2000); // Jan
        assertFalse(d21.isValid());
        Date d22 = new Date(3, 32, 2000); // Mar
        assertFalse(d22.isValid());
        Date d23 = new Date(4, 31, 2000); // Apr
        assertFalse(d23.isValid());
        Date d24 = new Date(5, 32, 2000); // May
        assertFalse(d24.isValid());
        Date d25 = new Date(6, 31, 2000); // Jun
        assertFalse(d25.isValid());
        Date d26 = new Date(7, 32, 2000); // Jul
        assertFalse(d26.isValid());
        Date d27 = new Date(8, 32, 2000); // Aug
        assertFalse(d27.isValid());
        Date d28 = new Date(9, 31, 2000); // Sep
        assertFalse(d28.isValid());
        Date d29 = new Date(10, 32, 2000); // Oct
        assertFalse(d29.isValid());
        Date d30 = new Date(11, 31, 2000); // Nov
        assertFalse(d30.isValid());
        Date d31 = new Date(12, 32, 2000); // Dec
        assertFalse(d31.isValid());
        
        // Invalid dates under day limit
        Date d32 = new Date(1, 0, 2000); // Jan
        assertFalse(d32.isValid());
        Date d33 = new Date(3, 0, 2000); // Mar
        assertFalse(d33.isValid());
        Date d34 = new Date(4, 0, 2000); // Apr
        assertFalse(d34.isValid());
        Date d35 = new Date(5, 0, 2000); // May
        assertFalse(d35.isValid());
        Date d36 = new Date(6, 0, 2000); // Jun
        assertFalse(d36.isValid());
        Date d37 = new Date(7, 0, 2000); // Jul
        assertFalse(d37.isValid());
        Date d38 = new Date(8, 0, 2000); // Aug
        assertFalse(d38.isValid());
        Date d39 = new Date(9, 0, 2000); // Sep
        assertFalse(d39.isValid());
        Date d40 = new Date(10, 0, 2000); // Oct
        assertFalse(d40.isValid());
        Date d41 = new Date(11, 0, 2000); // Nov
        assertFalse(d41.isValid());
        Date d42 = new Date(12, 0, 2000); // Dec
        assertFalse(d42.isValid());

        //fail("Not yet implemented");
    }

}
