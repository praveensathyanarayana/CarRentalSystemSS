package com.carrental.service;

import com.carrental.domain.*;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import com.carrental.dto.BookingRequest;
import com.carrental.dto.BookingResponse;
import com.carrental.exception.BookingNotFoundException;
import com.carrental.exception.NoAvailableCarsException;
import com.carrental.factory.BookingFactory;
import com.carrental.repository.BookingRepository;
import com.carrental.repository.CarRepository;
import com.carrental.service.validator.BookingValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private BookingRepository bookingRepo;
    private CarRepository carRepo;
    private BookingFactory factory;
    private BookingValidator validator;
    private PricingService pricingService;
    private BookingService service;

    @BeforeEach
    void setUp() {
        bookingRepo = Mockito.mock(BookingRepository.class);
        carRepo = Mockito.mock(CarRepository.class);
        factory = Mockito.mock(BookingFactory.class);
        validator = Mockito.mock(BookingValidator.class);
        pricingService = Mockito.mock(PricingService.class);
        service = new BookingService(bookingRepo, carRepo, factory, validator, pricingService);
    }

    @Test
    void testBookCarSuccess() {
        // Setup request
        BookingRequest req = new BookingRequest("user@example.com", CarType.SEDAN, LocalDateTime.now().plusDays(1).toString(), 2);

        // Create car, VIN, and booking mocks with all required stubs
        VIN vin = new VIN("1HGCM82633A004352");
        Car car = Mockito.mock(Car.class);
        Mockito.when(car.getVin()).thenReturn(vin);
        Mockito.when(car.getType()).thenReturn(CarType.SEDAN);
        Mockito.when(car.isAvailableFor(Mockito.any(), Mockito.anyList())).thenReturn(true);
        Mockito.when(car.getMake()).thenReturn("Toyota");
        Mockito.when(car.getModel()).thenReturn("Camry");
        Mockito.when(car.getDailyRate()).thenReturn(50.0);

        Mockito.when(carRepo.findAll()).thenReturn(Collections.singletonList(car));
        Mockito.when(bookingRepo.findAll()).thenReturn(Collections.emptyList());

        Booking booking = Mockito.mock(Booking.class);
        Mockito.when(factory.createBooking(Mockito.eq(car), Mockito.any(), Mockito.any())).thenReturn(booking);
        Mockito.doNothing().when(bookingRepo).save(Mockito.any());

        // Setup required nested mocks for BookingResponse.from
        Mockito.when(booking.getCar()).thenReturn(car);
        Mockito.when(booking.getUserEmail()).thenReturn(new Email("user@example.com"));
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusDays(2);
        Mockito.when(booking.getRentalPeriod()).thenReturn(new RentalPeriod(start, end));
        Mockito.when(booking.getBookingId()).thenReturn("booking-123");
        Mockito.when(booking.getAmountCharged()).thenReturn(100.0);
        Mockito.when(booking.isReturned()).thenReturn(false);

        // Now run the service and test
        BookingResponse response = service.bookCar(req);
        assertNotNull(response);
        assertEquals("booking-123", response.bookingId());
        assertEquals("1HGCM82633A004352", response.carVin());
        assertEquals("user@example.com", response.userEmail());
        assertEquals(100.0, response.amountCharged());
        assertFalse(response.returned());
    }

    @Test
    void testBookCarNoCarsThrows() {
        BookingRequest req = new BookingRequest("user@example.com", CarType.SEDAN, LocalDateTime.now().plusDays(1).toString(), 2);
        Mockito.when(carRepo.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(bookingRepo.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NoAvailableCarsException.class, () -> service.bookCar(req));
    }

    @Test
    void testReturnCarNotFoundThrows() {
        Mockito.when(bookingRepo.findById("bad-id")).thenReturn(Optional.empty());
        assertThrows(BookingNotFoundException.class, () -> service.returnCar("bad-id"));
    }

    @Test
    void testGetAllBookingsReturnsList() {
        Mockito.when(bookingRepo.findAll()).thenReturn(Collections.emptyList());
        assertNotNull(service.getAllBookings());
    }

    @Test
    void testGetBookingsByUserReturnsList() {
        Mockito.when(bookingRepo.findByUserEmail(Mockito.any())).thenReturn(Collections.emptyList());
        assertNotNull(service.getBookingsByUser("test@example.com"));
    }

    @Test
    void testGetBookingByIdThrowsIfIdInvalid() {
        assertThrows(IllegalArgumentException.class, () -> service.getBookingById(""));
    }

    @Test
    void testGetBookingByIdThrowsIfNotFound() {
        Mockito.when(bookingRepo.findById("missing")).thenReturn(Optional.empty());
        assertThrows(BookingNotFoundException.class, () -> service.getBookingById("missing"));
    }

    @Test
    void testGetActiveBookingsReturnsList() {
        Mockito.when(bookingRepo.findActiveBookings()).thenReturn(Collections.emptyList());
        assertNotNull(service.getActiveBookings());
    }
}
