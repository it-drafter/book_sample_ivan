package com.iktpreobuka.book_sample_ivan.repositories;

import com.iktpreobuka.book_sample_ivan.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
}
