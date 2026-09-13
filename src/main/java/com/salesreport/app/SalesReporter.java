package com.salesreport.app;

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

        System.out.println("Arguments validated successfully.");
        // wiring code - next commit
    }
}