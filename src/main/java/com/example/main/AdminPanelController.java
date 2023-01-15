package com.example.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    public MovieServiceImpl movieService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin-panel")
    public String admin_panel(){
        return "redirect:/find-user";
    }

    @GetMapping("/find-user")
    public String findUserPage(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "find-user";
    }


    @GetMapping("/add-movie")
    public String addMoviePage(Model model){
        model.addAttribute("movie",new Movie());
        return "add-movie";
    }

        @PostMapping("/add-movie")
    public String addMovie(@RequestParam("title") String title,
                                @RequestParam("description") String description
            ,final @RequestParam("image") MultipartFile file) throws IOException {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setImage(file.getBytes());
        movieService.save(movie);
        return "redirect:/add-movie";
    }


    @GetMapping("/delete-movie")
    public String deleteMoviePage(){
        return "delete-movie";
    }

    @DeleteMapping("/delete-movie")
    public String deleteMovie(@RequestParam(name = "id") Long id) {
        movieService.deleteMovieById(id);
        return "redirect:/delete-movie";
    }

    @GetMapping("/all-movies")
    public String allMovies(Model model){
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies",movies);
        return "all-movies";
    }
}
