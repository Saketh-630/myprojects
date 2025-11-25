package com.moviebooking.repository;

import com.moviebooking.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

    // List screens for a specific theatre
    List<Screen> findByTheatreId(Long theatreId);
}
