package com.iktpreobuka.book_sample_ivan.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "Book_Author", joinColumns = {
            @JoinColumn(name = "Author_id", nullable=false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "Book_id", nullable=false)
            }
    )
    protected List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author() {
        super();
        this.books = new ArrayList<>();
    }

}