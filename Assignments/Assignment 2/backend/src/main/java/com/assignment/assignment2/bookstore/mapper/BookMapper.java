package com.assignment.assignment2.bookstore.mapper;

import com.assignment.assignment2.bookstore.dto.BookDTO;
import com.assignment.assignment2.bookstore.model.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(
            target = "genre",
            source = "genre.name"
    )
    BookDTO bookToBookDTO(Book book);

    @InheritInverseConfiguration
    Book bookDTOToBook(BookDTO bookDTO);
}
