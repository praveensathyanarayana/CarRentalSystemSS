package com.carrental.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class VIN {
    private final String value;

    public VIN(String vin) {
        if (vin == null || vin.trim().isEmpty()) {
            throw new IllegalArgumentException("VIN is required");
        }
        String normalizedVin = vin.toUpperCase();
        if (!isValid(normalizedVin)) {
            throw new IllegalArgumentException("VIN must be exactly 17 alphanumeric characters (excluding I, O, Q)");
        }
        this.value = normalizedVin;
    }

    private boolean isValid(String vin) {
        return vin.matches("^[A-HJ-NPR-Z0-9]{17}$");
    }

    @Override
    public String toString() {
        return value;
    }
}
