package com.carrental.service;

import com.carrental.domain.Car;
import com.carrental.domain.CarType;
import com.carrental.domain.vo.VIN;
import com.carrental.dto.CarRequest;
import com.carrental.dto.CarResponse;
import com.carrental.exception.CarHasActiveBookingsException;
import com.carrental.exception.CarNotFoundException;
import com.carrental.exception.DuplicateCarException;
import com.carrental.repository.BookingRepository;
import com.carrental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public synchronized CarResponse addCar(CarRequest request) {
        VIN vin = new VIN(request.vin());

        if (carRepository.findByVin(vin).isPresent()) {
            throw new DuplicateCarException(vin.getValue());
        }

        Car car = new Car(vin, request.type(), request.make(), request.model(), request.dailyRate());
        carRepository.save(car);

        return CarResponse.from(car);
    }

    public synchronized List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarResponse::from)
                .collect(Collectors.toList());
    }

    public synchronized CarResponse updateCar(CarRequest request) {
        VIN vin = new VIN(request.vin());

        Car car = carRepository.findByVin(vin)
                .orElseThrow(() -> new CarNotFoundException(vin.getValue()));

        car.updateDetails(request.type(), request.make(), request.model(), request.dailyRate());
        carRepository.save(car);

        return CarResponse.from(car);
    }

    public synchronized void deleteCar(String vinString) {
        VIN vin = new VIN(vinString);

        if (!bookingRepository.findActiveBookingsByCarVin(vin).isEmpty()) {
            throw new CarHasActiveBookingsException(vin.getValue());
        }

        Car car = carRepository.findByVin(vin)
                .orElseThrow(() -> new CarNotFoundException(vin.getValue()));

        carRepository.delete(car);
    }

    public synchronized CarResponse getCarByVin(String vinString) {
        VIN vin = new VIN(vinString);
        Car car = carRepository.findByVin(vin)
                .orElseThrow(() -> new CarNotFoundException(vin.getValue()));
        return CarResponse.from(car);
    }

    public synchronized List<Car> getCarsByType(CarType type) {
        return carRepository.findAll().stream()
                .filter(c -> c.getType() == type)
                .collect(Collectors.toList());
    }
}