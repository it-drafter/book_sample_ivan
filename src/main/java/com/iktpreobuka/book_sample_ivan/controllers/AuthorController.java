package com.iktpreobuka.book_sample_ivan.controllers;

import com.iktpreobuka.book_sample_ivan.entities.Author;
import com.iktpreobuka.book_sample_ivan.entities.dto.AuthorDTO;
import com.iktpreobuka.book_sample_ivan.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/author")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(method= RequestMethod.POST)
    public AuthorDTO createAuthor(@RequestBody Author author) {
        Author a = new Author();
        a.setName(author.getName());
        authorRepository.save(a);
        return new AuthorDTO(a);
    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<AuthorDTO> getAllAuthors() {
        Iterable<Author> l = authorRepository.findAll();
        ArrayList<AuthorDTO> ll = new ArrayList<>();
        for(Author a : l) {
            ll.add(new AuthorDTO(a));
        }
        return ll;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        Optional<Author> o = authorRepository.findById(id);
        if(o.isPresent()){
            return new ResponseEntity<AuthorDTO>(new AuthorDTO(o.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<AuthorDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<AuthorDTO> removeAuthor(@PathVariable Long id) {
        Optional<Author> o = authorRepository.findById(id);
        if(o.isPresent()){
            authorRepository.deleteById(id);
            return new ResponseEntity<AuthorDTO>(new AuthorDTO(o.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<AuthorDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> o = authorRepository.findById(id);
        if(o.isPresent()){
            Author a = o.get();
            a.setName(author.getName());
            authorRepository.save(a);
            return new ResponseEntity<AuthorDTO>(new AuthorDTO(a), HttpStatus.OK);
        }else{
            return new ResponseEntity<AuthorDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/byname")
    public Iterable<AuthorDTO> getAuthorByName(@RequestParam String name) {
        Iterable<Author> l = authorRepository.findByName(name);
        ArrayList<AuthorDTO> ll = new ArrayList<>();
        for(Author a : l) {
            ll.add(new AuthorDTO(a));
        }
        return ll;
    }

    @RequestMapping(method=RequestMethod.GET, value="/search")
    public Iterable<AuthorDTO> searchAuthorByName(@RequestParam String name) {
        Iterable<Author> l = authorRepository.findByNameContainingIgnoreCase(name);
        ArrayList<AuthorDTO> ll = new ArrayList<>();
        for(Author g : l) {
            ll.add(new AuthorDTO(g));
        }
        return ll;
    }
}
