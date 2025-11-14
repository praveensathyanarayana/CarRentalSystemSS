package com.carrental.controller.query;

import com.carrental.dto.CarResponse;
import com.carrental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars/queries")
@RequiredArgsConstructor
public class CarQueryController {

    private final CarService carService;

    @GetMapping("/list")
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/by-vin")
    public CarResponse getCarByVin(@RequestParam String vin) {
        return carService.getCarByVin(vin);
    }
}