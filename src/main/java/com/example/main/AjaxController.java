package com.example.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AjaxController {

    @Autowired
    MovieServiceImpl movieService;
    @Autowired
    UserServiceImpl userService;

    @Transactional
    @GetMapping("/movie-title")
    public ResponseEntity<Movie> getMovieTitle(@RequestParam(name = "id",required = false) Long id) {
        try {
            Movie movie = movieService.findMovieById(id).get();
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @GetMapping("/username")
    public ResponseEntity<String> getUserUsername(@RequestParam(name = "id",required = false) Long id) {
        try {
            User user = userService.findUserById(id).get();
            return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @GetMapping("/search-movies")
    public ResponseEntity<List<Movie>> getMoviesList(@RequestParam(name = "title",required = false) String title) {
        try {
            List<Movie> movies = movieService.findMoviesByTitleIsContaining(title);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
