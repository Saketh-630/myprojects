package com.moviebooking.controller;

import com.moviebooking.entity.Movie;
import com.moviebooking.entity.Show;
import com.moviebooking.service.MovieService;
import com.moviebooking.service.ShowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private final MovieService movieService;
    private final ShowService showService;

    public HomeController(MovieService movieService, ShowService showService) {
        this.movieService = movieService;
        this.showService = showService;
    }

    // Home page - list NOW_SHOWING movies
    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {
        List<Movie> nowShowing = movieService.getMoviesByStatus("NOW_SHOWING");
        model.addAttribute("movies", nowShowing);

        // for navbar (user info)
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "index";
    }

    // Movie details + list of shows
    @GetMapping("/movie/{id}")
    public String showMovieDetails(@PathVariable Long id,
                                   @RequestParam(required = false) String date,
                                   Model model,
                                   HttpSession session) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return "redirect:/";
        }

        LocalDate showDate;
        if (date != null) {
            showDate = LocalDate.parse(date);
        } else {
            showDate = LocalDate.now();
        }

        List<Show> shows = showService.getShowsByMovieAndDate(id, showDate);

        model.addAttribute("movie", movie);
        model.addAttribute("shows", shows);
        model.addAttribute("selectedDate", showDate);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "movie_details";
    }

    // Simple user dashboard / booking history page placeholder
    @GetMapping("/user/home")
    public String userHome(Model model, HttpSession session) {
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "user_home";
    }
}
