package com.carrental.repository.impl;

import com.carrental.domain.User;
import com.carrental.domain.vo.Email;
import com.carrental.storage.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FileUserRepositoryTest {
    private FileStorageService storage;
    private FileUserRepository repo;

    @BeforeEach
    void setUp() {
        storage = Mockito.mock(FileStorageService.class);
        Mockito.when(storage.loadUsers()).thenReturn(Collections.emptyList());
        repo = new FileUserRepository(storage);
    }

    @Test
    void testFindByEmailEmpty() {
        assertFalse(repo.findByEmail(new Email("test@example.com")).isPresent());
    }

    @Test
    void testFindAllReturnsList() {
        assertEquals(Collections.emptyList(), repo.findAll());
    }
}
