package com.iktpreobuka.book_sample_ivan.controllers;

import com.iktpreobuka.book_sample_ivan.entities.Author;
import com.iktpreobuka.book_sample_ivan.entities.Book;
import com.iktpreobuka.book_sample_ivan.entities.Genre;
import com.iktpreobuka.book_sample_ivan.entities.dto.BookDTO;
import com.iktpreobuka.book_sample_ivan.repositories.AuthorRepository;
import com.iktpreobuka.book_sample_ivan.repositories.BookRepository;
import com.iktpreobuka.book_sample_ivan.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(method= RequestMethod.POST)
    public BookDTO createBook(@RequestBody BookDTO dto) {
        Book b = new Book();
        b.setIsbn(dto.getIsbn());
        b.setRating(dto.getRating());
        b.setTitle(dto.getTitle());
        b.setYear(dto.getYear());
        List<Genre> lg = genreRepository.findByName(dto.getGenre());
        if(lg.isEmpty()){
            Genre g = new Genre();
            g.setName(dto.getGenre());
            genreRepository.save(g);
            b.setGenre(g);
        }else {
            b.setGenre(lg.get(0));
        }

        for(String a : dto.getAuthors()) {
            List<Author> la = authorRepository.findByName(a);
            if(la.isEmpty()) {
                Author na = new Author();
                na.setName(a);
                authorRepository.save(na);
                b.getAuthors().add(na);
            }else {
                b.getAuthors().add(la.get(0));
            }
        }
        bookRepository.save(b);
        return new BookDTO(b);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<BookDTO> getAllBooks() {
        Iterable<Book> l = bookRepository.findAll();
        ArrayList<BookDTO> ll = new ArrayList<>();
        for(Book b : l) {
            ll.add(new BookDTO(b));
        }
        return ll;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        Optional<Book> o = bookRepository.findById(id);
        if(o.isPresent()){
            return new ResponseEntity<BookDTO>(new BookDTO(o.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<BookDTO> removeBook(@PathVariable Long id) {
        Optional<Book> o = bookRepository.findById(id);
        if(o.isPresent()){
            bookRepository.deleteById(id);
            return new ResponseEntity<BookDTO>(new BookDTO(o.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        Optional<Book> o = bookRepository.findById(id);
        if(o.isPresent()){
            Book b = o.get();
            b.setIsbn(dto.getIsbn());
            b.setRating(dto.getRating());
            b.setTitle(dto.getTitle());
            b.setYear(dto.getYear());
            List<Genre> lg = genreRepository.findByName(dto.getGenre());
            if(lg.isEmpty()){
                Genre g = new Genre();
                g.setName(dto.getGenre());
                genreRepository.save(g);
                b.setGenre(g);
            }else {
                b.setGenre(lg.get(0));
            }
            b.getAuthors().clear();
            for(String a : dto.getAuthors()) {
                List<Author> la = authorRepository.findByName(a);
                if(la.isEmpty()) {
                    Author na = new Author();
                    na.setName(a);
                    authorRepository.save(na);
                    b.getAuthors().add(na);
                }else {
                    b.getAuthors().add(la.get(0));
                }
            }
            bookRepository.save(b);
            return new ResponseEntity<BookDTO>(new BookDTO(b), HttpStatus.OK);
        }else{
            return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/search")
    public Iterable<BookDTO> searchBookByName(@RequestParam String title) {
        Iterable<Book> l = bookRepository.findByTitleContainingIgnoreCase(title);
        ArrayList<BookDTO> ll = new ArrayList<>();
        for(Book g : l) {
            ll.add(new BookDTO(g));
        }
        return ll;
    }

}
