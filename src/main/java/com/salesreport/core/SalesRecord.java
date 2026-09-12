package com.salesreport.core;

/**
 * Represents a single row of product sales data read from the CSV file.
 *
 * This is a plain data holder (immutable) that also knows how to
 * calculate its own revenue, since "revenue for this record" is a
 * property of the record itself rather than something an outside
 * class should reach in and compute from raw fields.
 */
public class SalesRecord {

    private final String productId;
    private final String productName;
    private final String category;
    private final int quantitySold;
    private final double unitPrice;

    public SalesRecord(String productId, String productName, String category,
                        int quantitySold, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Revenue contributed by this single product = quantity sold * unit price.
     */
    public double getRevenue() {
        return quantitySold * unitPrice;
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", quantitySold=" + quantitySold +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
