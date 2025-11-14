package com.carrental.dto;

import com.carrental.domain.CarType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CarRequest(
        @NotBlank(message = "VIN is required")
        @Pattern(regexp = "^[A-HJ-NPR-Z0-9]{17}$", message = "VIN must be exactly 17 alphanumeric characters (excluding I, O, Q)")
        String vin,

        @NotNull(message = "Car type is required")
        CarType type,

        @NotBlank(message = "Make is required")
        String make,

        @NotBlank(message = "Model is required")
        String model,

        @Positive(message = "Daily rate must be positive")
        double dailyRate
) {
}
