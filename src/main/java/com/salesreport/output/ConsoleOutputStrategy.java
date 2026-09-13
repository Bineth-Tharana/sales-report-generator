package com.salesreport.output;

/**
 * Writes the report to standard console output.
 */
public class ConsoleOutputStrategy implements OutputStrategy {
    @Override
    public void write(String report) {
        System.out.println(report);
    }
}