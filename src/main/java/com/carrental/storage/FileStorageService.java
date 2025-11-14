package com.carrental.storage;

import com.carrental.domain.Booking;
import com.carrental.domain.Car;
import com.carrental.domain.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileStorageService {
    private static final String STORAGE_DIR = "storage";
    private static final String CARS_FILE = STORAGE_DIR + File.separator + "cars.json";
    private static final String BOOKINGS_FILE = STORAGE_DIR + File.separator + "bookings.json";
    private static final String USERS_FILE = STORAGE_DIR + File.separator + "users.json";

    private final ObjectMapper mapper = new ObjectMapper();

    public FileStorageService() {
        // Create storage directory if it doesn't exist
        File directory = new File(STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public synchronized List<Car> loadCars() {
        return load(CARS_FILE, new TypeReference<List<Car>>() {
        });
    }

    public synchronized void saveCars(List<Car> cars) {
        save(CARS_FILE, cars);
    }

    public synchronized List<Booking> loadBookings() {
        return load(BOOKINGS_FILE, new TypeReference<List<Booking>>() {
        });
    }

    public synchronized void saveBookings(List<Booking> bookings) {
        save(BOOKINGS_FILE, bookings);
    }

    public synchronized List<User> loadUsers() {
        return load(USERS_FILE, new TypeReference<>() {
        });
    }

    public synchronized void saveUsers(List<User> users) {
        save(USERS_FILE, users);
    }

    private <T> List<T> load(String fileName, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(fileName);
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fileName, e);
        }
    }

    private <T> void save(String fileName, List<T> data) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save " + fileName, e);
        }
    }
}
