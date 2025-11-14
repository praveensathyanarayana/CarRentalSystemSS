package com.carrental.repository.impl;

import com.carrental.domain.Car;
import com.carrental.domain.vo.VIN;
import com.carrental.repository.CarRepository;
import com.carrental.storage.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileCarRepository implements CarRepository {
    private final FileStorageService fileStorageService;

    @Override
    public Optional<Car> findByVin(VIN vin) {
        List<Car> cars = fileStorageService.loadCars();
        return cars.stream()
                .filter(c -> c.getVin().equals(vin))
                .findFirst();
    }

    @Override
    public List<Car> findAll() {
        return fileStorageService.loadCars();
    }

    @Override
    public void save(Car car) {
        List<Car> cars = fileStorageService.loadCars();
        cars.removeIf(c -> c.getVin().equals(car.getVin()));
        cars.add(car);
        fileStorageService.saveCars(cars);
    }

    @Override
    public void delete(Car car) {
        List<Car> cars = fileStorageService.loadCars();
        cars.removeIf(c -> c.getVin().equals(car.getVin()));
        fileStorageService.saveCars(cars);
    }

    @Override
    public void saveAll(List<Car> cars) {
        fileStorageService.saveCars(cars);
    }
}
