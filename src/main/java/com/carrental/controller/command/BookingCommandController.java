package com.carrental.controller.command;

import com.carrental.dto.BookingRequest;
import com.carrental.dto.BookingResponse;
import com.carrental.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings/commands")
@RequiredArgsConstructor
@Validated
public class BookingCommandController {

    private final BookingService bookingService;

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse bookCar(@Valid @RequestBody BookingRequest request) {
        return bookingService.bookCar(request);
    }

    @PostMapping("/return")
    public double returnCar(@RequestParam String bookingId) {
        return bookingService.returnCar(bookingId);
    }
}