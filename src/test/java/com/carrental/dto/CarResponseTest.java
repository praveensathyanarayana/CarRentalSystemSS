package com.carrental.dto;

import com.carrental.domain.Car;
import com.carrental.domain.CarType;
import com.carrental.domain.vo.VIN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarResponseTest {

    @Test
    void testCarResponseFromCar() {
        Car car = new Car(new VIN("1HGCM82633A004352"), CarType.SUV, "Honda", "Pilot", 155.0);
        CarResponse response = CarResponse.from(car);
        assertEquals("1HGCM82633A004352", response.vin());
        assertEquals(CarType.SUV, response.type());
        assertEquals("Honda", response.make());
        assertEquals("Pilot", response.model());
        assertEquals(155.0, response.dailyRate());
    }
}
