package feg.assigment.app;

import java.util.Scanner;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, CsvException {
        Scanner sc = new Scanner(System.in);
        String file = sc.nextLine();
        // Replace with the path to your CSV file
        String filePath = "D:\\Java kurz\\Java projects\\FEG_CaseStudy_Java\\src\\main\\customers\\" + file + (".csv");

        //reads the CSV file and outputs the customer report
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).build()) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                System.out.println(lineInArray[0]+":" + " $"+ lineInArray[1].trim() );
            }
        }
        //parses the CSV file and returns a list of Customer objects
    }
}