package com.salesreport.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for one thing only: turning a CSV file on disk into a
 * List of SalesRecord objects.
 *
 * Expected CSV columns (header row is skipped):
 * product_id, product_name, category, quantity_sold, unit_price
 *
 * Note: this class focuses on the "happy path" of reading well-formed
 * data, since row-level validation / graceful error handling is being
 * layered on separately as part of the group's exception-handling work.
 */
public class SalesDataLoader {

    /**
     * Reads the CSV file at the given path and converts each data row
     * into a SalesRecord. The first line is always treated as a header
     * and skipped.
     *
     * @param filePath path to the CSV file
     * @return list of parsed sales records, in the order they appear in the file
     * @throws IOException if the file cannot be read
     */
    public List<SalesRecord> load(String filePath) throws IOException {
        List<SalesRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                SalesRecord record = parseLine(line);
                records.add(record);
            }
        }

        return records;
    }

    /**
     * Parses a single CSV line into a SalesRecord.
     * Assumes columns in the order:
     * product_id, product_name, category, quantity_sold, unit_price
     */
    private SalesRecord parseLine(String line) {
        String[] fields = line.split(",");

        String productId = fields[0].trim();
        String productName = fields[1].trim();
        String category = fields[2].trim();
        int quantitySold = Integer.parseInt(fields[3].trim());
        double unitPrice = Double.parseDouble(fields[4].trim());

        return new SalesRecord(productId, productName, category, quantitySold, unitPrice);
    }
}
