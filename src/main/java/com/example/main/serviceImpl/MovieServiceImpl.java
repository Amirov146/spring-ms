package com.example.main.serviceImpl;

import com.example.main.model.Comment;
import com.example.main.model.Movie;
import com.example.main.model.User;
import com.example.main.repository.CommentRepository;
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
    private MovieRepository movieRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserServiceImpl userService;

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

    public void addComment(Long movieId, String text, Long parentId, String username) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        User user = userService.findUserByUsername(username);

        Comment comment = new Comment();
        comment.setText(text);
        comment.setMovie(movie);
        comment.setUser(user);

        if (parentId != null) {
            Comment parent = commentRepository.findById(parentId).orElseThrow();
            comment.setParentComment(parent);
        }

        commentRepository.save(comment);
    }
}
