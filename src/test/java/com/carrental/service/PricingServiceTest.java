package com.carrental.service;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.RentalPeriod;
import com.carrental.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {

    private PricingStrategy pricingStrategy;
    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        pricingStrategy = Mockito.mock(PricingStrategy.class);
        pricingService = new PricingService(pricingStrategy);
    }

    @Test
    void testCalculateChargeDelegatesToStrategy() {
        Booking booking = Mockito.mock(Booking.class);
        Car car = Mockito.mock(Car.class);
        RentalPeriod period = new RentalPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));

        Mockito.when(booking.getRentalPeriod()).thenReturn(period);
        Mockito.when(booking.getCar()).thenReturn(car);
        Mockito.when(pricingStrategy.calculatePrice(period, car)).thenReturn(123.45);

        double result = pricingService.calculateCharge(booking);
        assertEquals(123.45, result);
    }
}
