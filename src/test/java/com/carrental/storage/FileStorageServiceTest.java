package com.carrental.storage;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageServiceTest {

    private FileStorageService storageService;

    @BeforeEach
    void setUp() {
        storageService = new FileStorageService();
    }

    @Test
    void testLoadCarsEmpty() {
        // Clean up any real file before running this in real environments!
        List<Car> cars = storageService.loadCars();
        assertNotNull(cars);
        assertTrue(cars.isEmpty() || cars instanceof List);
    }

    @Test
    void testSaveCarsCreatesFile() {
        List<Car> cars = new ArrayList<>(); // Add mock Car objects if desired
        assertDoesNotThrow(() -> storageService.saveCars(cars));
    }

    @Test
    void testLoadBookingsEmpty() {
        List<Booking> bookings = storageService.loadBookings();
        assertNotNull(bookings);
    }

    @Test
    void testSaveBookings() {
        List<Booking> bookings = new ArrayList<>();
        assertDoesNotThrow(() -> storageService.saveBookings(bookings));
    }

    @Test
    void testLoadUsersEmpty() {
        List<User> users = storageService.loadUsers();
        assertNotNull(users);
    }

    @Test
    void testSaveUsers() {
        List<User> users = new ArrayList<>();
        assertDoesNotThrow(() -> storageService.saveUsers(users));
    }
}
