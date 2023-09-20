package feg.assigment;

import feg.assigment.app.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import feg.assigment.app.Customer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AppTest {

        @Test
        public void testParseCSV() throws NoSuchMethodException, InvocationTargetException,IllegalAccessException {
            App app = new App();
            // Use reflection to access the private method
            Method method = App.class.getDeclaredMethod("parseCSV", String.class);
            method.setAccessible(true);

            // Substitute the name of the test CSV file
            String filename = "./src/customers/file.csv";
            List<Customer> customers = (List<Customer>) method.invoke(app, filename);

            // Check that the list of customers is not empty
            assertNotNull(customers);

            // Check that the list of customers is filled in correctly
            assertEquals(3, customers.size());

            // Check that the elements of the list has the expected values
            Customer firstCustomer = customers.get(0);
            assertEquals("John Smith", firstCustomer.getName());
            assertEquals(100.0, firstCustomer.getRevenue(), 0.001);

        }

        @Test
        public void testCalculateRevenue() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
            App app = new App();

            // Create a sample list of customers
            List<Customer> customers = List.of(
                    new Customer("John Smith", 100.0),
                    new Customer("Jane Doe", 200.0),
                    new Customer("Bob Johnson", 50.0)
            );

            // Use reflection to access the private method
            Method method = App.class.getDeclaredMethod("calculateRevenue", List.class);
            method.setAccessible(true);

            // Call the private method
            Map<String, Double> revenueMap = (Map<String, Double>) method.invoke(app, customers);

            // Check if the map contains expected data
            assertEquals(3, revenueMap.size());
            assertTrue(revenueMap.containsKey("John Smith"));
            assertEquals(100.0, revenueMap.get("John Smith"));
        }

        @Test
        public void testPrintReport() throws NoSuchMethodException, InvocationTargetException,IllegalAccessException {
            App app = new App();
            Map<String, Double> revenueMap = Map.of(
                    "John Smith", 100.0,
                    "Jane Doe", 200.0,
                    "Bob Johnson", 50.0
            );
            // Call the method and capture the console output
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            System.setOut(new PrintStream(output));
            Method method = App.class.getDeclaredMethod("printReport", Map.class);
            method.setAccessible(true);
            method.invoke(app, revenueMap);

            // Verify the output contains expected data
            String consoleOutput = output.toString();
            assertTrue(consoleOutput.contains("John Smith: $100.0"));
            assertTrue(consoleOutput.contains("Jane Doe: $200.0"));
            assertTrue(consoleOutput.contains("Bob Johnson: $50.0"));
        }
    }