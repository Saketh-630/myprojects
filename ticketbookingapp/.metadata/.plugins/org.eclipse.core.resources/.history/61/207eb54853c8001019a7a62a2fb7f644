package com.moviebooking.service.impl;

import com.moviebooking.entity.Show;
import com.moviebooking.repository.ShowRepository;
import com.moviebooking.service.ShowService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepo;

    public ShowServiceImpl(ShowRepository showRepo) {
        this.showRepo = showRepo;
    }

    @Override
    public Show saveShow(Show show) {
        return showRepo.save(show);
    }

    @Override
    public Show getShowById(Long id) {
        Optional<Show> optional = showRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Show> getAllShows() {
        return showRepo.findAll();
    }

    @Override
    public List<Show> getShowsByMovie(Long movieId) {
        return showRepo.findByMovieId(movieId);
    }

    @Override
    public List<Show> getShowsByMovieAndDate(Long movieId, LocalDate date) {
        return showRepo.findByMovieIdAndShowDate(movieId, date);
    }

    @Override
    public List<Show> getShowsByScreenAndDate(Long screenId, LocalDate date) {
        return showRepo.findByScreenIdAndShowDate(screenId, date);
    }

    @Override
    public void deleteShowById(Long id) {
        showRepo.deleteById(id);
    }
}
