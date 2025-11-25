package com.moviebooking.service;

import com.moviebooking.entity.Show;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {

    Show saveShow(Show show);

    Show getShowById(Long id);

    List<Show> getAllShows();

    List<Show> getShowsByMovie(Long movieId);

    List<Show> getShowsByMovieAndDate(Long movieId, LocalDate date);

    List<Show> getShowsByScreenAndDate(Long screenId, LocalDate date);

    void deleteShowById(Long id);
}

