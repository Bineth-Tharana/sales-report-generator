package com.salesreport.core;

import java.util.Map;

/**
 * Builds the human-readable report text from a SalesSummary.
 *
 * This class only knows how to turn numbers into a formatted String.
 * It does not care whether that String ends up printed to the console
 * or written to a file - that decision belongs to the output layer
 * (see the output-strategy classes being built separately).
 */
public class SalesReportBuilder {

    private static final String DIVIDER = "============================================";

    public String build(SalesSummary summary) {
        StringBuilder report = new StringBuilder();

        appendTitle(report);
        appendRevenuePerProduct(report, summary);
        appendRevenuePerCategory(report, summary);
        appendHighlights(report, summary);
        report.append(DIVIDER).append(System.lineSeparator());

        return report.toString();
    }

    private void appendTitle(StringBuilder report) {
        report.append(DIVIDER).append(System.lineSeparator());
        report.append(" PRODUCT SALES SUMMARY REPORT").append(System.lineSeparator());
        report.append(DIVIDER).append(System.lineSeparator());
    }

    private void appendRevenuePerProduct(StringBuilder report, SalesSummary summary) {
        report.append("--- Revenue Per Product ---").append(System.lineSeparator());

        for (SalesRecord record : summary.getRecords()) {
            report.append(String.format("%-6s %-18s %-14s $%8.2f%n",
                    record.getProductId(),
                    record.getProductName(),
                    record.getCategory(),
                    record.getRevenue()));
        }

        report.append(System.lineSeparator());
    }

    private void appendRevenuePerCategory(StringBuilder report, SalesSummary summary) {
        report.append("--- Revenue Per Category ---").append(System.lineSeparator());

        for (Map.Entry<String, Double> entry : summary.getRevenueByCategory().entrySet()) {
            report.append(String.format("%-14s : $%.2f%n", entry.getKey(), entry.getValue()));
        }

        report.append(System.lineSeparator());
    }

    private void appendHighlights(StringBuilder report, SalesSummary summary) {
        report.append("--- Highlights ---").append(System.lineSeparator());

        SalesRecord bestSeller = summary.getBestSellingProduct();
        SalesRecord highestRevenue = summary.getHighestRevenueProduct();

        report.append(String.format("Best-Selling Product : %s (%d units)%n",
                bestSeller.getProductName(), bestSeller.getQuantitySold()));

        report.append(String.format("Highest Revenue      : %s ($%.2f)%n",
                highestRevenue.getProductName(), highestRevenue.getRevenue()));

        report.append(String.format("Grand Total Revenue  : $%.2f%n", summary.getGrandTotalRevenue()));
    }
}
