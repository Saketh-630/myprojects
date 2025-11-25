package com.moviebooking.service;

import com.moviebooking.entity.Theatre;

import java.util.List;

public interface TheatreService {

    Theatre saveTheatre(Theatre theatre);

    Theatre getTheatreById(Long id);

    List<Theatre> getAllTheatres();

    List<Theatre> getTheatresByCity(String city);

    void deleteTheatreById(Long id);
}
