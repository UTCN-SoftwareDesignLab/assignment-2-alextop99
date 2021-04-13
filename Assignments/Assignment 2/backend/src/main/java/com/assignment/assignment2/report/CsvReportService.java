package com.assignment.assignment2.report;

import com.assignment.assignment2.bookstore.dto.BookDTO;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.assignment.assignment2.bookstore.BookConstants.BookTableHeader;
import static com.assignment.assignment2.report.ReportType.CSV;

@Service
public class CsvReportService implements ReportService {
    @Override
    public String export(List<BookDTO> noStockBooks) {
        LocalDateTime now = LocalDateTime.now();

        String filename = dtf.format(now) + ".csv";

        File csvFile = new File(filename);

        try {
            FileWriter csvFileOutput = new FileWriter(csvFile);
            CSVWriter csvWriter = new CSVWriter(csvFileOutput);

            csvWriter.writeNext(BookTableHeader);

            for(BookDTO book : noStockBooks) {
                String[] bookToWrite = {String.valueOf(book.getId()), book.getTitle(), book.getAuthor(), book.getGenre(), String.valueOf(book.getPrice()), String.valueOf(book.getStock())};
                csvWriter.writeNext(bookToWrite);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }

        return filename;
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
