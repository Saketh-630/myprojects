package com.moviebooking.service;

import com.moviebooking.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking saveBooking(Booking booking);

    Booking getBookingById(Long id);

    List<Booking> getBookingsByUser(Long userId);

    List<Booking> getBookingsByShow(Long showId);

    List<Booking> getAllBookings();

    void cancelBooking(Long bookingId);
}

