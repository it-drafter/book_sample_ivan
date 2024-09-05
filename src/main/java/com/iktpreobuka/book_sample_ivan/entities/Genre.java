package com.iktpreobuka.book_sample_ivan.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String name;

    @OneToMany(mappedBy = "genre", fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
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
    public Genre() {
        super();
        this.books = new ArrayList<>();
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }




}