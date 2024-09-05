package com.iktpreobuka.book_sample_ivan.controllers;

import com.iktpreobuka.book_sample_ivan.entities.Genre;
import com.iktpreobuka.book_sample_ivan.entities.dto.GenreDTO;
import com.iktpreobuka.book_sample_ivan.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/genre")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {
    @Autowired
    GenreRepository genreRepository;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<GenreDTO> createGenre(@RequestBody Genre genre) {
        List<Genre> existingGenre = genreRepository.findByName(genre.getName());

        if(!existingGenre.isEmpty()){
            return new ResponseEntity<GenreDTO>(HttpStatus.CONFLICT);
        }else{
            Genre g = new Genre();
            g.setName(genre.getName());
            genreRepository.save(g);
            return new ResponseEntity<GenreDTO>(new GenreDTO(g), HttpStatus.CREATED);
        }

    }

    @RequestMapping(method=RequestMethod.GET)
    public Iterable<GenreDTO> getAllGenres() {
        Iterable<Genre> l = genreRepository.findAll();
        ArrayList<GenreDTO> ll = new ArrayList<>();
        for(Genre g : l) {
            ll.add(new GenreDTO(g));
        }
        return ll;
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable Long id) {
        Optional<Genre> o = genreRepository.findById(id);
        if(o.isPresent()){
            return new ResponseEntity<GenreDTO>(new GenreDTO(o.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<GenreDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<GenreDTO> removeGenre(@PathVariable Long id) {
        Optional<Genre> o = genreRepository.findById(id);
        if(o.isPresent()){
            genreRepository.deleteById(id);
            return new ResponseEntity<GenreDTO>(new GenreDTO(o.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<GenreDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        Optional<Genre> o = genreRepository.findById(id);
        if(o.isPresent()){
            Genre g = o.get();
            g.setName(genre.getName());
            genreRepository.save(g);
            return new ResponseEntity<GenreDTO>(new GenreDTO(g), HttpStatus.OK);
        }else{
            return new ResponseEntity<GenreDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/byname")
    public Iterable<GenreDTO> getGenreByName(@RequestParam String name) {
        Iterable<Genre> l = genreRepository.findByName(name);
        ArrayList<GenreDTO> ll = new ArrayList<>();
        for(Genre g : l) {
            ll.add(new GenreDTO(g));
        }
        return ll;
    }

    @RequestMapping(method=RequestMethod.GET, value="/search")
    public Iterable<GenreDTO> searchGenreByName(@RequestParam String name) {
        Iterable<Genre> l = genreRepository.findByNameContainingIgnoreCase(name);
        ArrayList<GenreDTO> ll = new ArrayList<>();
        for(Genre g : l) {
            ll.add(new GenreDTO(g));
        }
        return ll;
    }
}
