package com.carrental.service;

import com.carrental.domain.Car;
import com.carrental.domain.CarType;
import com.carrental.domain.vo.VIN;
import com.carrental.dto.CarRequest;
import com.carrental.dto.CarResponse;
import com.carrental.exception.DuplicateCarException;
import com.carrental.repository.BookingRepository;
import com.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarRepository carRepo;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carRepo = Mockito.mock(CarRepository.class);
        BookingRepository bookingRepo = Mockito.mock(BookingRepository.class);
        carService = new CarService(carRepo, bookingRepo);
    }

    @Test
    void testAddCarSuccess() {
        CarRequest req = new CarRequest("1HGCM82633A004352", CarType.SEDAN, "Toyota", "Camry", 100.0);
        Mockito.when(carRepo.findByVin(Mockito.any())).thenReturn(Optional.empty());

        Car car = Mockito.mock(Car.class);
        Mockito.doNothing().when(carRepo).save(Mockito.any());
        Mockito.when(car.getVin()).thenReturn(new VIN("1HGCM82633A004352"));
        Mockito.when(car.getType()).thenReturn(CarType.SEDAN);
        Mockito.when(car.getMake()).thenReturn("Toyota");
        Mockito.when(car.getModel()).thenReturn("Camry");
        Mockito.when(car.getDailyRate()).thenReturn(100.0);

        CarResponse response = CarResponse.from(car);
        assertNotNull(response);
    }

    @Test
    void testAddCarDuplicateThrows() {
        CarRequest req = new CarRequest("1HGCM82633A004352", CarType.SEDAN, "Toyota", "Camry", 100.0);
        Mockito.when(carRepo.findByVin(Mockito.any())).thenReturn(Optional.of(Mockito.mock(Car.class)));
        assertThrows(DuplicateCarException.class, () -> carService.addCar(req));
    }

    @Test
    void testGetAllCarsReturnsList() {
        Mockito.when(carRepo.findAll()).thenReturn(Collections.emptyList());
        assertNotNull(carService.getAllCars());
    }
}
