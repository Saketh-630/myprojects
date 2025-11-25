package com.moviebooking.controller;

import com.moviebooking.entity.*;
import com.moviebooking.repository.BookingSeatRepository;
import com.moviebooking.service.BookingService;
import com.moviebooking.service.ShowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final ShowService showService;
    private final BookingService bookingService;
    private final BookingSeatRepository bookingSeatRepository;

    public BookingController(ShowService showService,
                             BookingService bookingService,
                             BookingSeatRepository bookingSeatRepository) {
        this.showService = showService;
        this.bookingService = bookingService;
        this.bookingSeatRepository = bookingSeatRepository;
    }

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    // Step 1: show seat layout
    @GetMapping("/start/{showId}")
    public String startBooking(@PathVariable Long showId,
                               Model model,
                               HttpSession session) {

        User user = getLoggedInUser(session);
        if (user == null) {
            return "redirect:/login";
        }

        Show show = showService.getShowById(showId);
        if (show == null) {
            return "redirect:/";
        }

        Screen screen = show.getScreen();
        int totalRows = screen.getTotalRows();
        int seatsPerRow = screen.getSeatsPerRow();

        // already booked seats
        List<BookingSeat> bookedSeats = bookingSeatRepository.findByShowId(showId);
        Set<String> booked = bookedSeats.stream()
                .map(bs -> bs.getSeatRow() + "-" + bs.getSeatNumber())
                .collect(Collectors.toSet());

        model.addAttribute("show", show);
        model.addAttribute("screen", screen);
        model.addAttribute("totalRows", totalRows);
        model.addAttribute("seatsPerRow", seatsPerRow);
        model.addAttribute("bookedSeats", booked); // set of "row-seat"
        model.addAttribute("loggedInUser", user);

        return "booking_seats";
    }

    // Step 2: confirm booking
    @PostMapping("/confirm")
    public String confirmBooking(@RequestParam Long showId,
                                 @RequestParam(name = "selectedSeats", required = false) List<String> selectedSeats,
                                 Model model,
                                 HttpSession session) {

        User user = getLoggedInUser(session);
        if (user == null) {
            return "redirect:/login";
        }

        Show show = showService.getShowById(showId);
        if (show == null) {
            return "redirect:/";
        }

        if (selectedSeats == null || selectedSeats.isEmpty()) {
            model.addAttribute("error", "Please select at least one seat.");
            return startBooking(showId, model, session);
        }

        // Re-check booked seats to avoid race conditions
        Set<String> alreadyBooked = bookingSeatRepository.findByShowId(showId).stream()
                .map(bs -> bs.getSeatRow() + "-" + bs.getSeatNumber())
                .collect(Collectors.toSet());

        // If any selected seat is already booked, show error
        for (String s : selectedSeats) {
            if (alreadyBooked.contains(s)) {
                model.addAttribute("error", "Some selected seats are no longer available. Please choose again.");
                return startBooking(showId, model, session);
            }
        }

        // Create booking
        Booking booking = new Booking();
        booking.setBookingTime(LocalDateTime.now());
        booking.setUser(user);
        booking.setShow(show);
        booking.setStatus("CONFIRMED");

        // total amount
        double basePrice = show.getTicketPrice();
        double totalAmount = 0.0;

        List<BookingSeat> seatEntities = new ArrayList<>();

        for (String seat : selectedSeats) {
            String[] parts = seat.split("-");
            Integer row = Integer.parseInt(parts[0]); // 1-based row
            Integer num = Integer.parseInt(parts[1]); // seat number

            // Determine tier based on row
            double seatPrice;
            if (row <= 3) {                    // Premium rows (A, B)
                seatPrice = basePrice + 50;
            } else if (row <= 7) {             // Gold rows (C, D, E)
                seatPrice = basePrice;
            } else {                           // Standard (rest)
                seatPrice = Math.max(basePrice - 30, 0); // avoid negative
            }

            totalAmount += seatPrice;

            BookingSeat bs = new BookingSeat();
            bs.setSeatRow(row);
            bs.setSeatNumber(num);
            bs.setBooking(booking);
            seatEntities.add(bs);
        }

        booking.setSeats(seatEntities);
        booking.setTotalAmount(totalAmount);

        Booking saved = bookingService.saveBooking(booking);

        return "redirect:/booking/success/" + saved.getId();
    }

    // Step 3: success page
    @GetMapping("/success/{bookingId}")
    public String bookingSuccess(@PathVariable Long bookingId,
                                 Model model,
                                 HttpSession session) {

        User user = getLoggedInUser(session);
        if (user == null) return "redirect:/login";

        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) return "redirect:/";

        model.addAttribute("booking", booking);
        model.addAttribute("loggedInUser", user);

        return "booking_success";
    }

    // User booking history
    @GetMapping("/my-bookings")
    public String myBookings(Model model, HttpSession session) {
        User user = getLoggedInUser(session);
        if (user == null) return "redirect:/login";

        List<Booking> bookings = bookingService.getBookingsByUser(user.getId());
        model.addAttribute("bookings", bookings);
        model.addAttribute("loggedInUser", user);
        return "user_bookings";
    }
}
