package com.salesreport.output;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes the report to a text file at the given file path.
 */
public class FileOutputStrategy implements OutputStrategy {

    private final String filePath;

    public FileOutputStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(String report) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(report);
        }
    }
}