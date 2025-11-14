package com.carrental.service;

import com.carrental.domain.Booking;
import com.carrental.pricing.PricingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricingService {
    private final PricingStrategy pricingStrategy;

    public double calculateCharge(Booking booking) {
        return pricingStrategy.calculatePrice(
                booking.getRentalPeriod(),
                booking.getCar()
        );
    }
}
