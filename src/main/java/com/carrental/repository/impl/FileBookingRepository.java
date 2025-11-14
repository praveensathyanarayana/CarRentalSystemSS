package com.carrental.repository.impl;

import com.carrental.domain.Booking;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import com.carrental.repository.BookingRepository;
import com.carrental.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FileBookingRepository implements BookingRepository {
    private final FileStorageService fileStorageService;

    @Override
    public Optional<Booking> findById(String bookingId) {
        List<Booking> bookings = fileStorageService.loadBookings();
        return bookings.stream()
                .filter(b -> b.getBookingId().equals(bookingId))
                .findFirst();
    }

    @Override
    public List<Booking> findAll() {
        return fileStorageService.loadBookings();
    }

    @Override
    public List<Booking> findByUserEmail(Email email) {
        List<Booking> bookings = fileStorageService.loadBookings();
        return bookings.stream()
                .filter(b -> b.getUserEmail().equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findActiveBookings() {
        List<Booking> bookings = fileStorageService.loadBookings();
        return bookings.stream()
                .filter(Booking::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findActiveBookingsByCarVin(VIN vin) {
        List<Booking> bookings = fileStorageService.loadBookings();
        return bookings.stream()
                .filter(Booking::isActive)
                .filter(b -> b.getCar().getVin().equals(vin))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Booking booking) {
        List<Booking> bookings = fileStorageService.loadBookings();
        bookings.removeIf(b -> b.getBookingId().equals(booking.getBookingId()));
        bookings.add(booking);
        fileStorageService.saveBookings(bookings);
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        fileStorageService.saveBookings(bookings);
    }
}
