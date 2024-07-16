package com.example.main.serviceImpl;

import com.example.main.model.Movie;
import com.example.main.repository.MovieRepository;
import com.example.main.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    public MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findMoviesByTitleIsContaining(String title) {
        return movieRepository.findMoviesByTitleIsContaining(title);
    }


    public Optional<Movie> findMovieById(Long id){
        return movieRepository.findById(id);
    }

}
