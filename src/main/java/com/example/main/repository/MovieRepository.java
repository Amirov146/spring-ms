package com.example.main.repository;

import com.example.main.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie,Long> {

    Movie save(Movie movie);
    Page<Movie> findAll(Pageable pageable);
    List<Movie> findAll();
    List<Movie> findMoviesByTitleIsContaining(String title);
}
