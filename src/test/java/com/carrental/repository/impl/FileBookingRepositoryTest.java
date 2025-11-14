package com.carrental.repository.impl;

import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.VIN;
import com.carrental.storage.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FileBookingRepositoryTest {
    private FileStorageService storage;
    private FileBookingRepository repo;

    @BeforeEach
    void setUp() {
        storage = Mockito.mock(FileStorageService.class);
        Mockito.when(storage.loadBookings()).thenReturn(Collections.emptyList());
        repo = new FileBookingRepository(storage);
    }

    @Test
    void testFindByIdEmpty() {
        assertFalse(repo.findById("nonexistent").isPresent());
    }

    @Test
    void testFindAllReturnsList() {
        assertEquals(Collections.emptyList(), repo.findAll());
    }

    @Test
    void testFindByUserEmailEmpty() {
        assertEquals(Collections.emptyList(), repo.findByUserEmail(new Email("none@example.com")));
    }

    @Test
    void testFindActiveBookingsEmpty() {
        assertEquals(Collections.emptyList(), repo.findActiveBookings());
    }

    @Test
    void testFindActiveBookingsByCarVinEmpty() {
        assertEquals(Collections.emptyList(), repo.findActiveBookingsByCarVin(new VIN("1HGCM82633A004352")));
    }
}
