package com.iktpreobuka.book_sample_ivan.repositories;

import com.iktpreobuka.book_sample_ivan.entities.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByName(String name);
    List<Genre> findByNameContainingIgnoreCase(String name);
}