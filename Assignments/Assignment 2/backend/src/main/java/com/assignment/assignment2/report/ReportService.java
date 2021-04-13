package com.assignment.assignment2.report;

import com.assignment.assignment2.bookstore.dto.BookDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface ReportService {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    String export(List<BookDTO> noStockBooks);
    ReportType getType();
}