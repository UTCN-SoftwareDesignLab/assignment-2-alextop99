package com.assignment.assignment2.report;

import com.assignment.assignment2.bookstore.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.assignment.assignment2.report.ReportType.CSV;
import static com.assignment.assignment2.report.ReportType.PDF;

@SpringBootTest
class ReportServiceFactoryTest {

    @Autowired
    private ReportServiceFactory reportServiceFactory;

    @Autowired
    private BookService bookService;

    @Test
    void getReportService() {
        ReportService csvReportService = reportServiceFactory.getReportService(CSV);
        Assertions.assertEquals("Succeeded", csvReportService.export(bookService.findNoStockBooks()));

        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
        Assertions.assertEquals("Succeeded", pdfReportService.export(bookService.findNoStockBooks()));
    }
}