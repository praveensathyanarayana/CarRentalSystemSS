package com.carrental.repository;

import com.carrental.domain.Car;
import com.carrental.domain.vo.VIN;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    Optional<Car> findByVin(VIN vin);

    List<Car> findAll();

    void save(Car car);

    void delete(Car car);

    void saveAll(List<Car> cars);
}
