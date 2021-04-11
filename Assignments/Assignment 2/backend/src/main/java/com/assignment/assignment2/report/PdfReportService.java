package com.assignment.assignment2.report;

import com.assignment.assignment2.bookstore.dto.BookDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.assignment.assignment2.report.ReportType.PDF;

@Service
public class PdfReportService implements ReportService {

    @Override
    public String export(List<BookDTO> books) {
        try (PDDocument noStockPDF = new PDDocument()) {
            PDPage page = new PDPage();
            noStockPDF.addPage(page);

            try (PDPageContentStream cont = new PDPageContentStream(noStockPDF, page)) {
                cont.beginText();
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 700);

                cont.showText("ID, Title, Author, Genre, Price, Stock");
                cont.newLine();

                for (BookDTO book: books) {
                    cont.showText(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getGenre() + ", " + book.getPrice() + ", " + book.getStock());
                    cont.newLine();
                }

                cont.endText();
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            LocalDateTime now = LocalDateTime.now();

            noStockPDF.save(dtf.format(now) + ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }

        return "Succeeded";
    }


    @Override
    public ReportType getType() {
        return PDF;
    }
}
