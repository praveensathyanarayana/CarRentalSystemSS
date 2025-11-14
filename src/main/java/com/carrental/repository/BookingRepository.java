package com.carrental.repository;

import com.carrental.domain.Booking;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Optional<Booking> findById(String bookingId);

    List<Booking> findAll();

    List<Booking> findByUserEmail(Email email);

    List<Booking> findActiveBookings();

    List<Booking> findActiveBookingsByCarVin(VIN vin);

    void save(Booking booking);

    void saveAll(List<Booking> bookings);
}
