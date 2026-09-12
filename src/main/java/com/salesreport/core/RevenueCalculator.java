package com.salesreport.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Turns a list of SalesRecords into a SalesSummary.
 *
 * This class has exactly one job: perform the sales calculations
 * (revenue per product, revenue per category, best seller, highest
 * revenue product, grand total). It does not read files and it does
 * not format or print anything - that separation is what keeps this
 * class easy to unit test in isolation.
 */
public class RevenueCalculator {

    public SalesSummary calculate(List<SalesRecord> records) {
        if (records == null || records.isEmpty()) {
            return new SalesSummary(records, new LinkedHashMap<>(), null, null, 0.0);
        }

        Map<String, Double> revenueByCategory = calculateRevenueByCategory(records);
        SalesRecord bestSeller = findBestSellingProduct(records);
        SalesRecord highestRevenueProduct = findHighestRevenueProduct(records);
        double grandTotal = calculateGrandTotal(records);

        return new SalesSummary(records, revenueByCategory, bestSeller, highestRevenueProduct, grandTotal);
    }

    /**
     * Sums the revenue of every record into its category bucket.
     * A LinkedHashMap is used so categories appear in first-seen order,
     * which keeps the report output stable and predictable.
     */
    private Map<String, Double> calculateRevenueByCategory(List<SalesRecord> records) {
        Map<String, Double> categoryTotals = new LinkedHashMap<>();

        for (SalesRecord record : records) {
            categoryTotals.merge(record.getCategory(), record.getRevenue(), Double::sum);
        }

        return categoryTotals;
    }

    /**
     * The best-selling product is the one with the highest quantity_sold.
     */
    private SalesRecord findBestSellingProduct(List<SalesRecord> records) {
        SalesRecord best = records.get(0);

        for (SalesRecord record : records) {
            if (record.getQuantitySold() > best.getQuantitySold()) {
                best = record;
            }
        }

        return best;
    }

    /**
     * The highest revenue product is the one with the highest
     * (quantity_sold * unit_price), which is not necessarily the
     * same product as the best seller.
     */
    private SalesRecord findHighestRevenueProduct(List<SalesRecord> records) {
        SalesRecord highest = records.get(0);

        for (SalesRecord record : records) {
            if (record.getRevenue() > highest.getRevenue()) {
                highest = record;
            }
        }

        return highest;
    }

    /**
     * Grand total revenue is the sum of every product's revenue.
     */
    private double calculateGrandTotal(List<SalesRecord> records) {
        double total = 0.0;

        for (SalesRecord record : records) {
            total += record.getRevenue();
        }

        return total;
    }
}
