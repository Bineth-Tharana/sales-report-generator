package com.salesreport.output;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileOutputStrategyTest {

    @Test
    void testWritesReportToFile(@TempDir Path tempDir) throws Exception {
        Path outputFile = tempDir.resolve("report.txt");
        OutputStrategy strategy = new FileOutputStrategy(outputFile.toString());

        strategy.write("Sample Report Content");

        String content = Files.readString(outputFile);
        assertEquals("Sample Report Content", content);
    }
}