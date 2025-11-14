package com.carrental.dto;

import com.carrental.domain.User;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.PhoneNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserResponseTest {

    @Test
    void testUserResponseFromUser() {
        User user = new User("John Doe", new Email("john@example.com"), new PhoneNumber("+11234567890"), true);
        UserResponse response = UserResponse.from(user);

        assertEquals("John Doe", response.name());
        assertEquals("john@example.com", response.email());
        assertEquals("+11234567890", response.phone());
        assertTrue(response.active());
    }
}
