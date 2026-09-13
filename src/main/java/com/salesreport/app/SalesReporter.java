package com.salesreport.app;

import com.salesreport.core.SalesDataLoader;
import com.salesreport.core.SalesRecord;
import com.salesreport.core.RevenueCalculator;
import com.salesreport.core.SalesSummary;
import com.salesreport.core.SalesReportBuilder;
import com.salesreport.output.OutputStrategy;
import com.salesreport.output.ConsoleOutputStrategy;
import com.salesreport.output.FileOutputStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SalesReporter {

    public static void main(String[] args) {

        // --- Argument count validation ---
        if (args.length < 2 || args.length > 3) {
            System.err.println("Usage: java SalesReporter <csv-file-path> <output-method> [output-file-path]");
            System.exit(1);
        }

        String csvFilePath = args[0];
        String outputMethod = args[1].toLowerCase();
        String outputFilePath = (args.length == 3) ? args[2] : null;

        // --- Output method validation ---
        if (!outputMethod.equals("console") && !outputMethod.equals("file")) {
            System.err.println("Error: Invalid output method '" + outputMethod + "'. Must be 'console' or 'file'.");
            System.exit(1);
        }

        // --- File mode requires output path ---
        if (outputMethod.equals("file") && (outputFilePath == null || outputFilePath.isBlank())) {
            System.err.println("Error: Output file path is required when output method is 'file'.");
            System.exit(1);
        }

        // --- Load CSV data (with error handling) ---
        SalesDataLoader loader = new SalesDataLoader();
        List<SalesRecord> records = null;

        try {
            records = loader.load(csvFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: CSV file not found at path: " + csvFilePath);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error: Could not read the CSV file. " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Error: CSV file contains invalid numeric data (quantity/price).");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: CSV file has a row with missing columns.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error: Unexpected error while loading CSV file: " + e.getMessage());
            System.exit(1);
        }

        if (records == null || records.isEmpty()) {
            System.err.println("Error: No valid sales records found in the CSV file.");
            System.exit(1);
        }

        // --- Compute summary ---
        RevenueCalculator calculator = new RevenueCalculator();
        SalesSummary summary = calculator.calculate(records);

        // --- Build report text ---
        SalesReportBuilder builder = new SalesReportBuilder();
        String reportText = builder.build(summary);

        // --- Choose output strategy (OCP-friendly) ---
        OutputStrategy strategy = outputMethod.equals("console")
                ? new ConsoleOutputStrategy()
                : new FileOutputStrategy(outputFilePath);

        // --- Write output (with error handling) ---
        try {
            strategy.write(reportText);
        } catch (Exception e) {
            System.err.println("Error: Failed to write report output. " + e.getMessage());
            System.exit(1);
        }

        if (outputMethod.equals("file")) {
            System.out.println("Report successfully written to: " + outputFilePath);
        }
    }
}