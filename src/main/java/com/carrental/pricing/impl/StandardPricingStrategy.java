package com.carrental.pricing.impl;

import com.carrental.domain.Car;
import com.carrental.domain.RentalPeriod;
import com.carrental.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

@Component
public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(RentalPeriod period, Car car) {
        long days = period.getDays();
        return days * car.getDailyRate();
    }
}
