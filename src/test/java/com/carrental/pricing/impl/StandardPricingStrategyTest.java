package com.carrental.pricing.impl;

import com.carrental.domain.Car;
import com.carrental.domain.CarType;
import com.carrental.domain.RentalPeriod;
import com.carrental.domain.vo.VIN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StandardPricingStrategyTest {

    private StandardPricingStrategy strategy;
    private Car car;

    @BeforeEach
    void setUp() {
        strategy = new StandardPricingStrategy();
        car = new Car(new VIN("1HGCM82633A004352"), CarType.SEDAN, "Toyota", "Camry", 50.0);
    }

    @Test
    void testCalculatePriceMultipleDays() {
        RentalPeriod period = new RentalPeriod(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(4));
        double price = strategy.calculatePrice(period, car);
        assertEquals(3 * 50.0, price);
    }

    @Test
    void testCalculatePriceOneDayMinimum() {
        RentalPeriod period = new RentalPeriod(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(6));
        double price = strategy.calculatePrice(period, car);
        assertEquals(1 * 50.0, price); // Should be minimum of 1 day
    }

    @Test
    void testCalculateZeroDailyRate() {
        Car freeCar = new Car(new VIN("1HGCM82633A004352"), CarType.SEDAN, "Toyota", "Camry", 0.0);
        RentalPeriod period = new RentalPeriod(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        double price = strategy.calculatePrice(period, freeCar);
        assertEquals(1 * 0.0, price);
    }
}
