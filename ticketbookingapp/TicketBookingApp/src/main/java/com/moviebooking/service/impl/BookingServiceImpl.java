package com.moviebooking.service.impl;

import com.moviebooking.entity.Booking;
import com.moviebooking.repository.BookingRepository;
import com.moviebooking.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;

    public BookingServiceImpl(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        Optional<Booking> optional = bookingRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsByShow(Long showId) {
        return bookingRepo.findByShowId(showId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Optional<Booking> optional = bookingRepo.findById(bookingId);
        if (optional.isPresent()) {
            Booking booking = optional.get();
            booking.setStatus("CANCELLED");
            bookingRepo.save(booking);
        }
    }
}
