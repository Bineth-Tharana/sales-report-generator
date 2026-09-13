# Sales Report Generator

A command-line tool that reads daily product sales data from a CSV file, computes a summary report, and outputs the result either to the console or to a text file.

Built for **SENG 21222 – Software Construction**, University of Kelaniya (2026 Assignment 1).

## Features

- Computes total revenue per product (`quantity_sold × unit_price`)
- Computes total revenue per category
- Identifies the best-selling product (highest quantity sold)
- Identifies the highest revenue product
- Calculates grand total revenue across all products
- Outputs to console or to a file, based on a command-line argument
- Designed around an output-strategy interface so new output methods (e.g. email) can be added without modifying existing code

## Tech Stack

- Java 21
- Maven (build & dependency management)
- JUnit 5 (unit testing)

## Project Structure

```
sales-report-generator/
├── data/
│   └── sales.csv                  # Sample input data
├── src/
│   └── main/
│       └── java/
│           └── com/salesreport/core/
│               ├── SalesRecord.java         # Data model for one CSV row
│               ├── SalesDataLoader.java     # Reads and parses the CSV file
│               ├── SalesSummary.java        # Holds computed results
│               ├── RevenueCalculator.java   # Core calculation logic
│               └── SalesReportBuilder.java  # Formats the summary into report text
├── pom.xml
└── README.md
```

## Usage

```
java SalesReporter <csv-file-path> <output-method> [output-file-path]
```

| Argument | Description |
|---|---|
| `<csv-file-path>` | Path to the input CSV file |
| `<output-method>` | Either `console` or `file` |
| `[output-file-path]` | Required only when `output-method` is `file` |

**Example (console output):**
```
java SalesReporter data/sales.csv console
```

**Example (file output):**
```
java SalesReporter data/sales.csv file report.txt
```

## CSV Input Format

The CSV file must include a header row (which is skipped automatically) with the following columns:

```
product_id, product_name, category, quantity_sold, unit_price
```

Example:
```
P001, Wireless Mouse, Electronics, 12, 25.50
P002, Notebook, Stationery, 35, 3.75
```

## Sample Output

```
============================================
 PRODUCT SALES SUMMARY REPORT
============================================
--- Revenue Per Product ---
P001   Wireless Mouse     Electronics    $  306.00
P002   Notebook           Stationery     $  131.25
...

--- Revenue Per Category ---
Electronics    : $690.00
Stationery     : $181.25

--- Highlights ---
Best-Selling Product : Ballpoint Pen (100 units)
Highest Revenue      : Wireless Mouse ($306.00)
Grand Total Revenue  : $871.25
============================================
```

## Building and Running

```
mvn compile
mvn exec:java -Dexec.mainClass="com.salesreport.SalesReporter" -Dexec.args="data/sales.csv console"
```

Or build a runnable jar:
```
mvn package
java -cp target/sales-report-generator-1.0-SNAPSHOT.jar com.salesreport.SalesReporter data/sales.csv console
```

## Running Tests

```
mvn test
```

## Design Notes

- **Single Responsibility:** each class has one job — loading data, calculating revenue, or formatting the report.
- **Open-Closed:** output is handled through an interface, so adding a new output method (e.g. emailing the report) doesn't require changing existing calculation or formatting code.
- Error handling (missing files, malformed rows, invalid arguments) is layered on top of the core logic rather than mixed into it.

## Team

| Member | Role | GitHub Username |
|---|---|---|
| Member 1 | Core logic — CSV reading, revenue calculation, report generation | Bineth-Tharana |
| Member 2 | File I/O, unit testing, SOLID principles | *(add username)* |
| Member 3 | Console interface, exception handling, documentation | *(add username)* |

## License

Academic project for SENG 21222, University of Kelaniya. Not licensed for external use.
