package com.carrental.dto;

import com.carrental.domain.Booking;

import java.time.LocalDateTime;

public record BookingResponse(
        String bookingId,
        String carVin,
        String userEmail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        double amountCharged,
        boolean returned
) {
    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getBookingId(),
                booking.getCar() != null && booking.getCar().getVin() != null ? booking.getCar().getVin().getValue() : null,
                booking.getUserEmail() != null ? booking.getUserEmail().value() : null,
                booking.getRentalPeriod() != null ? booking.getRentalPeriod().getStartDateTime() : null,
                booking.getRentalPeriod() != null ? booking.getRentalPeriod().getEndDateTime() : null,
                booking.getAmountCharged(),
                booking.isReturned()
        );
    }

}
