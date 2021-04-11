package com.assignment.assignment2.bookstore;

import com.assignment.assignment2.bookstore.model.EGenre;
import com.assignment.assignment2.bookstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(EGenre genre);
}
