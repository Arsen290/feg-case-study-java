package feg.assigment.app;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;


public class App {
    public static void main(String[] args) throws IOException, CsvException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the file in the customers folder:");
        String file = sc.nextLine();
        // Replace with the path to your CSV file
        String filePath = "./src/customers/" + file + (".csv");

        //reads the CSV file and outputs the customer report
        try (FileWriter writer = new FileWriter("output.csv");
             CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()) {

            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                String name = lineInArray[0];
                double revenue = Double.parseDouble(lineInArray[1].trim());

                // Create string the customer report and save it in output.csv
                String customerReport = name + ":" + " $" + revenue + "\n";
                writer.write(customerReport);
            }
        }

        parseCSV(filePath);
        calculateRevenue(parseCSV(filePath));
        printReport(calculateRevenue(parseCSV(filePath)));
    }

    //parses the CSV file and returns a list
    private static List<Customer> parseCSV(String filename) throws IOException, CsvException {

        List<Customer> listCustomers = new ArrayList<>();

        listCustomers = new CsvToBeanBuilder(new FileReader(filename))
                .withSkipLines(1)
                .withType(Customer.class)
                .build()
                .parse();

        return listCustomers;
    }

    //calculates the revenue for each customer and returns a map of customer names to revenue values
    private static Map<String, Double> calculateRevenue(List<Customer> customers) {
        Map<String, Double> mapCustomers = new HashMap<>();
        for (Customer customer : customers) {
            mapCustomers.put(customer.getName(), customer.getRevenue());
        }
        return mapCustomers;
    }

    //prints the customer report to the console
    private static void printReport(Map<String, Double> revenueMap){
        revenueMap.forEach((name,revenue)-> System.out.println(name + ":" + " $" + revenue));
    }

}