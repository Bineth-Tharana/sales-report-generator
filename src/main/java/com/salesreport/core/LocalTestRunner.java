package com.salesreport.core;

import java.util.List;

public class LocalTestRunner {
    public static void main(String[] args) throws Exception {
        SalesDataLoader loader = new SalesDataLoader();
        List<SalesRecord> records = loader.load("data/sales.csv");

        RevenueCalculator calculator = new RevenueCalculator();
        SalesSummary summary = calculator.calculate(records);

        SalesReportBuilder builder = new SalesReportBuilder();
        System.out.println(builder.build(summary));
    }
}