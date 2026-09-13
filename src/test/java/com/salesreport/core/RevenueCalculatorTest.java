package com.salesreport.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RevenueCalculatorTest {

    private RevenueCalculator calculator;
    private List<SalesRecord> records;

    @BeforeEach
    void setUp() {
        calculator = new RevenueCalculator();
        records = new ArrayList<>();
        // Assignment PDF එකේ තිබෙන sample data එකම මෙතන use කරමු
        records.add(new SalesRecord("P001", "Wireless Mouse", "Electronics", 12, 25.50));
        records.add(new SalesRecord("P002", "Notebook", "Stationery", 35, 3.75));
        records.add(new SalesRecord("P003", "USB Hub", "Electronics", 8, 18.00));
        records.add(new SalesRecord("P004", "Ballpoint Pen", "Stationery", 100, 0.50));
        records.add(new SalesRecord("P005", "HDMI Cable", "Electronics", 20, 12.00));
    }

    @Test
    void testRevenuePerProduct() {
        assertEquals(306.00, records.get(0).getRevenue(), 0.001); // Wireless Mouse
        assertEquals(50.00, records.get(3).getRevenue(), 0.001);  // Ballpoint Pen
    }

    @Test
    void testGrandTotalRevenue() {
        SalesSummary summary = calculator.calculate(records);
        assertEquals(871.25, summary.getGrandTotalRevenue(), 0.001);
    }

    @Test
    void testRevenuePerCategory() {
        SalesSummary summary = calculator.calculate(records);
        assertEquals(690.00, summary.getRevenueByCategory().get("Electronics"), 0.001);
        assertEquals(181.25, summary.getRevenueByCategory().get("Stationery"), 0.001);
    }

    @Test
    void testBestSellingProduct() {
        SalesSummary summary = calculator.calculate(records);
        assertEquals("Ballpoint Pen", summary.getBestSellingProduct().getProductName());
        assertEquals(100, summary.getBestSellingProduct().getQuantitySold());
    }

    @Test
    void testHighestRevenueProduct() {
        SalesSummary summary = calculator.calculate(records);
        assertEquals("Wireless Mouse", summary.getHighestRevenueProduct().getProductName());
        assertEquals(306.00, summary.getHighestRevenueProduct().getRevenue(), 0.001);
    }

    @Test
    void testEmptyListDoesNotCrash() {
        List<SalesRecord> empty = new ArrayList<>();
        SalesSummary summary = calculator.calculate(empty);
        assertEquals(0.0, summary.getGrandTotalRevenue(), 0.001);
    }
}