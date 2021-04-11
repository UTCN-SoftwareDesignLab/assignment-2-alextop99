package com.assignment.assignment2.bookstore;

import com.assignment.assignment2.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b from Book b where b.stock = 0")
    List<Book> findNoStockBooks();
}
