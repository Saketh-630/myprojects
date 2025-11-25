package com.moviebooking.service.impl;

import com.moviebooking.entity.Movie;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;

    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> optional = movieRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public List<Movie> getMoviesByStatus(String status) {
        return movieRepo.findByStatus(status);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepo.deleteById(id);
    }
}

