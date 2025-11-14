package com.carrental.controller.command;

import com.carrental.dto.CarRequest;
import com.carrental.dto.CarResponse;
import com.carrental.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars/commands")
@RequiredArgsConstructor
public class CarCommandController {

    private final CarService carService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponse addCar(@Valid @RequestBody CarRequest request) {
        return carService.addCar(request);
    }

    @PutMapping("/update")
    public CarResponse updateCar(@Valid @RequestBody CarRequest request) {
        return carService.updateCar(request);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@RequestParam String vin) {
        carService.deleteCar(vin);
    }
}