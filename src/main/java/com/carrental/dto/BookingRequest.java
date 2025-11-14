package com.carrental.dto;

import com.carrental.domain.CarType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookingRequest(
        @NotBlank(message = "User email is required")
        @Email(message = "Invalid email format")
        String userEmail,

        @NotNull(message = "Car type is required")
        CarType carType,

        @NotBlank(message = "Start date time is required")
        String startDateTime,

        @Positive(message = "Number of days must be positive")
        int numberOfDays
) {
}
