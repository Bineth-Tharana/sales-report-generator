package com.salesreport.output;

/**
 * Strategy interface for outputting the sales report.
 * New output methods (e.g. email) can be added by implementing
 * this interface, without modifying existing code (Open-Closed Principle).
 */
public interface OutputStrategy {
    void write(String report) throws Exception;
}