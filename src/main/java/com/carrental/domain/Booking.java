package com.carrental.domain;

import com.carrental.domain.vo.Email;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @NotBlank(message = "Booking ID is required")
    private String bookingId;

    @NotNull(message = "Car is required")
    @Valid
    private Car car;

    @NotNull(message = "User email is required")
    private Email userEmail;

    @NotNull(message = "Rental period is required")
    @Valid
    private RentalPeriod rentalPeriod;

    private boolean returned;

    @PositiveOrZero(message = "Amount charged must be zero or positive")
    private double amountCharged;

    public Booking(Car car, Email userEmail, RentalPeriod rentalPeriod) {
        this.bookingId = UUID.randomUUID().toString();
        this.car = car;
        this.userEmail = userEmail;
        this.rentalPeriod = rentalPeriod;
        this.returned = false;
        this.amountCharged = 0.0;
    }

    public void markAsReturned(double charge) {
        if (this.returned) {
            throw new IllegalStateException("Booking already returned");
        }
        if (charge < 0) {
            throw new IllegalArgumentException("Charge cannot be negative");
        }
        this.returned = true;
        this.amountCharged = charge;
    }

    public boolean isActive() {
        return !returned;
    }
}
