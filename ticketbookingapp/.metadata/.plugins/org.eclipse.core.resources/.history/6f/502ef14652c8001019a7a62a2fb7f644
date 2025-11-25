package com.moviebooking.repository;

import com.moviebooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // User's booking history
    List<Booking> findByUserId(Long userId);

    // All bookings for a show (admin / analytics)
    List<Booking> findByShowId(Long showId);
}
