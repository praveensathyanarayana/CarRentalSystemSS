package com.carrental.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {

    @Test
    void testUserRequestInitialization() {
        UserRequest userReq = new UserRequest(
                "Alice Smith",
                "alice@example.com",
                "+11234567890",
                true
        );
        assertEquals("Alice Smith", userReq.name());
        assertEquals("alice@example.com", userReq.email());
        assertEquals("+11234567890", userReq.phone());
        assertTrue(userReq.active());
    }
}
