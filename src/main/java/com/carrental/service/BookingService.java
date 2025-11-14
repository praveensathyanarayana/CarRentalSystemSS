package com.carrental.service;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.RentalPeriod;
import com.carrental.domain.vo.Email;
import com.carrental.dto.BookingRequest;
import com.carrental.dto.BookingResponse;
import com.carrental.exception.BookingNotFoundException;
import com.carrental.exception.NoAvailableCarsException;
import com.carrental.factory.BookingFactory;
import com.carrental.repository.BookingRepository;
import com.carrental.repository.CarRepository;
import com.carrental.service.validator.BookingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final BookingFactory bookingFactory;
    private final BookingValidator bookingValidator;
    private final PricingService pricingService;

    public synchronized BookingResponse bookCar(BookingRequest request) {
        LocalDateTime startDateTime = LocalDateTime.parse(request.startDateTime());
        bookingValidator.validateBookingRequest(startDateTime, request.numberOfDays());

        Email userEmail = new Email(request.userEmail());
        RentalPeriod requestedPeriod = new RentalPeriod(
                startDateTime,
                startDateTime.plusDays(request.numberOfDays())
        );
        requestedPeriod.validate();

        List<Car> carsOfType = carRepository.findAll().stream()
                .filter(c -> c.getType() == request.carType())
                .collect(Collectors.toList());

        List<Booking> allBookings = bookingRepository.findAll();

        for (Car car : carsOfType) {
            if (car.isAvailableFor(requestedPeriod, allBookings)) {
                Booking booking = bookingFactory.createBooking(car, userEmail, requestedPeriod);
                bookingRepository.save(booking);
                return BookingResponse.from(booking);
            }
        }

        throw new NoAvailableCarsException(request.carType());
    }

    public synchronized double returnCar(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking ID is required");
        }

        Booking booking = bookingRepository.findById(bookingId)
                .filter(Booking::isActive)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        double amount = pricingService.calculateCharge(booking);
        booking.markAsReturned(amount);
        bookingRepository.save(booking);

        return amount;
    }

    public synchronized List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());
    }

    public synchronized List<BookingResponse> getBookingsByUser(String emailString) {
        Email email = new Email(emailString);
        return bookingRepository.findByUserEmail(email).stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());
    }

    public synchronized BookingResponse getBookingById(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking ID is required");
        }

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        return BookingResponse.from(booking);
    }

    public synchronized List<BookingResponse> getActiveBookings() {
        return bookingRepository.findActiveBookings().stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());
    }
}