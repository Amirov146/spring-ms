package com.example.main;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Movie save(Movie movie);
    Page<Movie> findAll(Pageable pageable);
    void deleteMovieById(Long id);
    List<Movie> findAll();
    List<Movie> findMoviesByTitleIsContaining(String title);
}
