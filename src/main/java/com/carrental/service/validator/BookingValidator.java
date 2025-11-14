package com.carrental.service.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookingValidator {
    public void validateBookingRequest(LocalDateTime startDateTime, int numberOfDays) {
        if (startDateTime == null) {
            throw new IllegalArgumentException("Start date time is required");
        }

        if (startDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date time cannot be in the past");
        }

        if (numberOfDays <= 0) {
            throw new IllegalArgumentException("Number of days must be positive");
        }

        if (numberOfDays > 365) {
            throw new IllegalArgumentException("Number of days cannot exceed 365");
        }
    }
}
