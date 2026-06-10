package com.example.main.controller;

import com.example.main.repository.MovieRepository;
import com.example.main.serviceImpl.MovieServiceImpl;
import com.example.main.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private MovieRepository movieRepository;

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/movie/display/{id}")
    @ResponseBody
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Movie> movie)
            throws IOException {
        log.info("Id :: " + id);
        movie = movieService.findMovieById(id);
        response.setContentType("movie/jpeg");
        response.getOutputStream().write(movie.get().getImage());
        response.getOutputStream().close();
    }

    @GetMapping("/main")
    public String show(Model map,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        Page<Movie> movie = movieRepository.findAll(PageRequest.of(page, size));
        map.addAttribute("movie", movie);
        return "main";
    }


    @GetMapping("/movie/{id}")
    public String movieDescription(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovieById(id).orElse(null);

        model.addAttribute("movie", movie);

        if (movie != null) {
            model.addAttribute("comments", movie.getComments());
        }

        return "movie";
    }

    @PostMapping("/movie/{id}/comment")
    public String addComment(@PathVariable("id") Long id,
                             @RequestParam("text") String text,
                             @RequestParam(value = "parentId", required = false) Long parentId,
                             Principal principal) {
        movieService.addComment(id, text, parentId, principal.getName());
        return "redirect:/movie/" + id;
    }

}
