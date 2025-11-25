package com.moviebooking.service.impl;

import com.moviebooking.entity.Theatre;
import com.moviebooking.repository.TheatreRepository;
import com.moviebooking.service.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepo;

    public TheatreServiceImpl(TheatreRepository theatreRepo) {
        this.theatreRepo = theatreRepo;
    }

    @Override
    public Theatre saveTheatre(Theatre theatre) {
        return theatreRepo.save(theatre);
    }

    @Override
    public Theatre getTheatreById(Long id) {
        Optional<Theatre> optional = theatreRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepo.findAll();
    }

    @Override
    public List<Theatre> getTheatresByCity(String city) {
        return theatreRepo.findByCityIgnoreCase(city);
    }

    @Override
    public void deleteTheatreById(Long id) {
        theatreRepo.deleteById(id);
    }
}

