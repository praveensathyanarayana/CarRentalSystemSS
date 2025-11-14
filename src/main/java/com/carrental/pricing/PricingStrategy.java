package com.carrental.pricing;

import com.carrental.domain.Car;
import com.carrental.domain.RentalPeriod;

public interface PricingStrategy {
    double calculatePrice(RentalPeriod period, Car car);
}
