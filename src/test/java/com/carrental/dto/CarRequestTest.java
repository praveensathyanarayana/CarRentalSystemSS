package com.carrental.dto;

import com.carrental.domain.CarType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarRequestTest {

    @Test
    void testCarRequestInitialization() {
        CarRequest carReq = new CarRequest(
                "1HGCM82633A004352",
                CarType.SUV,
                "Honda",
                "CR-V",
                120.0
        );
        assertEquals("1HGCM82633A004352", carReq.vin());
        assertEquals(CarType.SUV, carReq.type());
        assertEquals("Honda", carReq.make());
        assertEquals("CR-V", carReq.model());
        assertEquals(120.0, carReq.dailyRate());
    }
}
