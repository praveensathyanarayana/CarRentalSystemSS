package com.carrental.controller.query;

import com.carrental.dto.BookingResponse;
import com.carrental.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings/queries")
@RequiredArgsConstructor
public class BookingQueryController {

    private final BookingService bookingService;

    @GetMapping("/list")
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/by-user")
    public List<BookingResponse> getBookingsByUser(@RequestParam String email) {
        return bookingService.getBookingsByUser(email);
    }

    @GetMapping("/by-id")
    public BookingResponse getBookingById(@RequestParam String bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/active")
    public List<BookingResponse> getActiveBookings() {
        return bookingService.getActiveBookings();
    }
}