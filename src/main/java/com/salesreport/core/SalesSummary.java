package com.salesreport.core;

import java.util.List;
import java.util.Map;

/**
 * A simple container for the results produced by RevenueCalculator.
 * Keeping this separate from RevenueCalculator means the calculation
 * logic and the "shape of the result" can change independently.
 */
public class SalesSummary {

    private final List<SalesRecord> records;
    private final Map<String, Double> revenueByCategory;
    private final SalesRecord bestSellingProduct;
    private final SalesRecord highestRevenueProduct;
    private final double grandTotalRevenue;

    public SalesSummary(List<SalesRecord> records,
                         Map<String, Double> revenueByCategory,
                         SalesRecord bestSellingProduct,
                         SalesRecord highestRevenueProduct,
                         double grandTotalRevenue) {
        this.records = records;
        this.revenueByCategory = revenueByCategory;
        this.bestSellingProduct = bestSellingProduct;
        this.highestRevenueProduct = highestRevenueProduct;
        this.grandTotalRevenue = grandTotalRevenue;
    }

    public List<SalesRecord> getRecords() {
        return records;
    }

    public Map<String, Double> getRevenueByCategory() {
        return revenueByCategory;
    }

    public SalesRecord getBestSellingProduct() {
        return bestSellingProduct;
    }

    public SalesRecord getHighestRevenueProduct() {
        return highestRevenueProduct;
    }

    public double getGrandTotalRevenue() {
        return grandTotalRevenue;
    }
}
