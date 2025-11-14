package com.carrental.domain;

import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    private Email email;
    private PhoneNumber phone;

    @BeforeEach
    void setUp() {
        email = new Email("user@example.com");
        phone = new PhoneNumber("+12345678901");
        user = new User("John Doe", email, phone, true);
    }

    @Test
    void testUserInitialization() {
        assertEquals("John Doe", user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertTrue(user.isActive());
    }

    @Test
    void testDisableEnable() {
        user.disable();
        assertFalse(user.isActive());
        user.enable();
        assertTrue(user.isActive());
    }

    @Test
    void testUpdateDetailsValid() {
        PhoneNumber newPhone = new PhoneNumber("+19876543210");
        user.updateDetails("Jane Smith", newPhone);
        assertEquals("Jane Smith", user.getName());
        assertEquals(newPhone, user.getPhone());
    }

    @Test
    void testUpdateDetailsInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> user.updateDetails("", phone));
    }

    @Test
    void testUpdateDetailsInvalidPhone() {
        assertThrows(IllegalArgumentException.class, () -> user.updateDetails("Jane Smith", null));
    }

    @Test
    void testUpdateActive() {
        user.updateActive(false);
        assertFalse(user.isActive());
        user.updateActive(true);
        assertTrue(user.isActive());
    }
}
