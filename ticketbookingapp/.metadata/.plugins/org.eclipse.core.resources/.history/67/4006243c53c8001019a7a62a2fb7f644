package com.moviebooking.service.impl;

import com.moviebooking.entity.Screen;
import com.moviebooking.repository.ScreenRepository;
import com.moviebooking.service.ScreenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepo;

    public ScreenServiceImpl(ScreenRepository screenRepo) {
        this.screenRepo = screenRepo;
    }

    @Override
    public Screen saveScreen(Screen screen) {
        return screenRepo.save(screen);
    }

    @Override
    public Screen getScreenById(Long id) {
        Optional<Screen> optional = screenRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Screen> getAllScreens() {
        return screenRepo.findAll();
    }

    @Override
    public List<Screen> getScreensByTheatre(Long theatreId) {
        return screenRepo.findByTheatreId(theatreId);
    }

    @Override
    public void deleteScreenById(Long id) {
        screenRepo.deleteById(id);
    }
}
