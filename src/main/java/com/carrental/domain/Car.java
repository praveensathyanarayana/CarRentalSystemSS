package com.carrental.domain;

import com.carrental.domain.vo.VIN;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @NotNull(message = "VIN is required")
    private VIN vin;

    @NotNull(message = "Car type is required")
    private CarType type;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @Positive(message = "Daily rate must be positive")
    private double dailyRate;

    public boolean isAvailableFor(RentalPeriod period, List<Booking> existingBookings) {
        return existingBookings.stream()
                .filter(booking -> !booking.isReturned())
                .filter(booking -> booking.getCar().getVin().equals(this.vin))
                .noneMatch(booking -> booking.getRentalPeriod().overlapsWith(period));
    }

    public void updateDetails(CarType type, String make, String model, double dailyRate) {
        if (type == null) {
            throw new IllegalArgumentException("Car type is required");
        }
        if (make == null || make.trim().isEmpty()) {
            throw new IllegalArgumentException("Make is required");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model is required");
        }
        if (dailyRate <= 0) {
            throw new IllegalArgumentException("Daily rate must be positive");
        }
        this.type = type;
        this.make = make;
        this.model = model;
        this.dailyRate = dailyRate;
    }
}
