package com.assignment.assignment2.report;

import com.assignment.assignment2.bookstore.dto.BookDTO;

import java.util.List;

public interface ReportService {
    String export(List<BookDTO> noStockBooks);
    ReportType getType();
}
