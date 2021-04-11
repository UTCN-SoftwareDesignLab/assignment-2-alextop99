package com.assignment.assignment2.bookstore;

import com.assignment.assignment2.bookstore.dto.BookDTO;
import com.assignment.assignment2.bookstore.mapper.BookMapper;
import com.assignment.assignment2.bookstore.model.Book;
import com.assignment.assignment2.bookstore.model.EGenre;
import com.assignment.assignment2.bookstore.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper ::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO findById(Long id) {
        Optional<BookDTO> result = bookRepository.findById(id).map(bookMapper :: bookToBookDTO);
        return result.orElse(null);
    }

    public void deleteByID(long id) {
        bookRepository.deleteById(id);
    }

    public void save(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);

        Genre genre = genreRepository.findByName(EGenre.valueOf(bookDTO.getGenre()))
                .orElseThrow(() -> new RuntimeException("Cannot find role: " + bookDTO.getGenre()));;

        book.setGenre(genre);
        bookRepository.save(book);
    }

    public List<BookDTO> findNoStockBooks() {
        return bookRepository.findNoStockBooks().stream()
                .map(bookMapper ::bookToBookDTO)
                .collect(Collectors.toList());
    }
}
