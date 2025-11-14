package com.carrental.domain;

import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    private Car car;
    private VIN vin;
    private Email email;

    @BeforeEach
    public void setup() {
        vin = new VIN("VN123456789125421");
        car = new Car(vin, CarType.SEDAN, "Toyota", "Camry", 100.0);
        email = new Email("Test@Test.com");
    }

    @Test
    public void testIsAvailableFor_NoBookings_ReturnsTrue() {
        List<Booking> bookings = new ArrayList<>();
        RentalPeriod period = new RentalPeriod(
                LocalDateTime.of(2025, 11, 20, 10, 0),
                LocalDateTime.of(2025, 11, 25, 10, 0)
        );
        assertTrue(car.isAvailableFor(period, bookings));
    }

    @Test
    public void testIsAvailableFor_BookingForDifferentCar_ReturnsTrue() {
        RentalPeriod period = new RentalPeriod(
                LocalDateTime.of(2025, 11, 20, 10, 0),
                LocalDateTime.of(2025, 11, 25, 10, 0)
        );
        Car otherCar = new Car(new VIN("VN123456789125422"), CarType.SUV, "Ford", "Explorer", 120.0);
        Booking booking = new Booking(otherCar, email, period );
        List<Booking> bookings = List.of(booking);
        assertTrue(car.isAvailableFor(period, bookings));
    }

    @Test
    public void testIsAvailableFor_BookingOverlapping_ReturnsFalse() {
        RentalPeriod period = new RentalPeriod(
                LocalDateTime.of(2025, 11, 20, 10, 0),
                LocalDateTime.of(2025, 11, 25, 10, 0)
        );
        RentalPeriod overlapping = new RentalPeriod(
                LocalDateTime.of(2025, 11, 20, 10, 0),
                LocalDateTime.of(2025, 11, 25, 10, 0)
        );
        Booking booking = new Booking(car, email, overlapping);
        List<Booking> bookings = List.of(booking);
        assertFalse(car.isAvailableFor(period, bookings));
    }

    @Test
    public void testUpdateDetails_ValidValues_UpdatesSuccessfully() {
        car.updateDetails(CarType.SUV, "Honda", "CRV", 120.0);
        assertEquals(CarType.SUV, car.getType());
        assertEquals("Honda", car.getMake());
        assertEquals("CRV", car.getModel());
        assertEquals(120.0, car.getDailyRate());
    }

    @Test
    public void testUpdateDetails_NullType_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            car.updateDetails(null, "Honda", "CRV", 120.0);
        });
        assertEquals("Car type is required", exception.getMessage());
    }

    @Test
    public void testUpdateDetails_EmptyMake_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            car.updateDetails(CarType.SUV, " ", "CRV", 120.0);
        });
        assertEquals("Make is required", exception.getMessage());
    }

    @Test
    public void testUpdateDetails_EmptyModel_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            car.updateDetails(CarType.SUV, "Honda", "", 120.0);
        });
        assertEquals("Model is required", exception.getMessage());
    }

    @Test
    public void testUpdateDetails_NonPositiveDailyRate_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            car.updateDetails(CarType.SUV, "Honda", "CRV", 0);
        });
        assertEquals("Daily rate must be positive", exception.getMessage());
    }
}
