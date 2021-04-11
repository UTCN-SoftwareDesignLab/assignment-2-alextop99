package com.assignment.assignment2.controllers;

import com.assignment.assignment2.bookstore.BookService;
import com.assignment.assignment2.bookstore.dto.BookDTO;
import com.assignment.assignment2.controllers.dto.DeleteRequest;
import com.assignment.assignment2.report.ReportServiceFactory;
import com.assignment.assignment2.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.assignment.assignment2.UrlMapping.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(BOOKS)
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping(FIND_ALL)
    @Secured({"ADMIN", "EMPLOYEE"})
    public List<BookDTO> allItems() {
        return bookService.findAll();
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"ADMIN", "EMPLOYEE"})
    public BookDTO findById(@RequestParam Long id) {
        return bookService.findById(id);
    }

    @PostMapping(DELETE)
    @Secured({"ADMIN"})
    public void delete(@Valid @RequestBody DeleteRequest deleteRequest) {
        bookService.deleteByID(deleteRequest.getId());
    }

    @PostMapping(SAVE)
    @Secured({"ADMIN"})
    public void save(@Valid @RequestBody BookDTO bookDTO) {
        bookService.save(bookDTO);
    }

    @PostMapping(UPDATE)
    @Secured({"ADMIN"})
    public void update(@Valid @RequestBody BookDTO bookDTO) {
        bookService.save(bookDTO);
    }

    @GetMapping(REPORT)
    @Secured({"ADMIN"})
    public void generateReport(@PathVariable ReportType reportType) {
        reportServiceFactory.getReportService(reportType).export(bookService.findNoStockBooks());
    }

}
