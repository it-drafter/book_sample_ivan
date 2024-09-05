package com.iktpreobuka.book_sample_ivan.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    protected String title;
    protected String isbn;
    protected String year;
    protected Double rating;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "Book_Author", joinColumns = {
            @JoinColumn(name = "Book_id", nullable=false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "Author_id", nullable=false)
            }
    )
    protected List<Author> authors;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
    protected Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book() {
        super();
        this.authors = new ArrayList<>();
    }


}
