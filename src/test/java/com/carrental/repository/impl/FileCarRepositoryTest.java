package com.carrental.repository.impl;

import com.carrental.domain.Car;
import com.carrental.domain.vo.VIN;
import com.carrental.storage.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FileCarRepositoryTest {
    private FileStorageService storage;
    private FileCarRepository repo;

    @BeforeEach
    void setUp() {
        storage = Mockito.mock(FileStorageService.class);
        Mockito.when(storage.loadCars()).thenReturn(Collections.emptyList());
        repo = new FileCarRepository(storage);
    }

    @Test
    void testFindByVinEmpty() {
        assertFalse(repo.findByVin(new VIN("nonexistent")).isPresent());
    }

    @Test
    void testFindAllReturnsList() {
        assertEquals(Collections.emptyList(), repo.findAll());
    }
}
