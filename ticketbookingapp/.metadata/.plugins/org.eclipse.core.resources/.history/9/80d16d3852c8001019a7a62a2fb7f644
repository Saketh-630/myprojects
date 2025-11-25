package com.moviebooking.repository;

import com.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    // All shows for a movie
    List<Show> findByMovieId(Long movieId);

    // Shows for a movie on a particular date
    List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);

    // Shows in a screen on a date (useful to avoid overlapping timings if you want)
    List<Show> findByScreenIdAndShowDate(Long screenId, LocalDate showDate);
}
