package com.carrental.dto;

import com.carrental.domain.Car;
import com.carrental.domain.CarType;

public record CarResponse(
        String vin,
        CarType type,
        String make,
        String model,
        double dailyRate
) {
    public static CarResponse from(Car car) {
        return new CarResponse(
                car.getVin().getValue(),
                car.getType(),
                car.getMake(),
                car.getModel(),
                car.getDailyRate()
        );
    }
}
