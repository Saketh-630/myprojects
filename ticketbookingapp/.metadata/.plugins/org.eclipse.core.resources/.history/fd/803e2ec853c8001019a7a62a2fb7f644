package com.moviebooking.controller;

import com.moviebooking.entity.Movie;
import com.moviebooking.entity.User;
import com.moviebooking.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/movies")
public class AdminMovieController {

    private final MovieService movieService;

    public AdminMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null && "ADMIN".equalsIgnoreCase(user.getRole());
    }

    @GetMapping
    public String listMovies(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_movie_list";
    }

    @GetMapping("/new")
    public String showNewMovieForm(Model model, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("movie", new Movie());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_movie_form";
    }

    @PostMapping
    public String saveMovie(@ModelAttribute("movie") Movie movie,
                            HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        movieService.saveMovie(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id,
                            Model model,
                            HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return "redirect:/admin/movies";
        }
        model.addAttribute("movie", movie);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_movie_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id,
                              HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        movieService.deleteMovieById(id);
        return "redirect:/admin/movies";
    }
}
