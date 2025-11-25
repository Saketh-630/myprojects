package com.moviebooking.repository;

import com.moviebooking.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {

    // All booked seats for a particular show
    @Query("SELECT bs FROM BookingSeat bs WHERE bs.booking.show.id = :showId")
    List<BookingSeat> findByShowId(Long showId);
}
