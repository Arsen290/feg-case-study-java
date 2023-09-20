package feg.assigment;

import feg.assigment.app.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void testGetName() {
        Customer customer = new Customer("Vlad Arsenyuk", 100.0);
        assertEquals("Vlad Arsenyuk", customer.getName());
    }

    @Test
    public void testGetRevenue() {
        Customer customer = new Customer("Honza Vins", 200);
        assertEquals(200.0, customer.getRevenue(), 0.01);
    }

}