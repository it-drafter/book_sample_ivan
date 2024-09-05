package com.iktpreobuka.book_sample_ivan.repositories;

import com.iktpreobuka.book_sample_ivan.entities.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByName(String name);
    List<Author> findByNameContainingIgnoreCase(String name);
}
