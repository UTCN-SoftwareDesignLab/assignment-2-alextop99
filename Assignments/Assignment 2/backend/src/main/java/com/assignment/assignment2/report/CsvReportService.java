package com.assignment.assignment2.report;

import com.assignment.assignment2.bookstore.dto.BookDTO;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.assignment.assignment2.report.ReportType.CSV;

@Service
public class CsvReportService implements ReportService {
    @Override
    public String export(List<BookDTO> noStockBooks) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        File csvFile = new File(dtf.format(now) + ".csv");

        try {
            FileWriter csvFileOutput = new FileWriter(csvFile);
            CSVWriter csvWriter = new CSVWriter(csvFileOutput);

            String[] header = {"Title", "Author", "Genre", "Price", "Stock"};
            csvWriter.writeNext(header);

            for(BookDTO book : noStockBooks) {
                String[] bookToWrite = {book.getTitle(), book.getAuthor(), book.getGenre(), String.valueOf(book.getPrice()), String.valueOf(book.getStock())};
                csvWriter.writeNext(bookToWrite);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }

        return "Succeeded";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
