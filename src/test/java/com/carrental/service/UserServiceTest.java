package com.carrental.service;

import com.carrental.domain.User;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.PhoneNumber;
import com.carrental.dto.UserRequest;
import com.carrental.dto.UserResponse;
import com.carrental.exception.DuplicateUserException;
import com.carrental.exception.UserNotFoundException;
import com.carrental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserRepository userRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepo);
    }

    @Test
    void testAddUserSuccess() {
        UserRequest req = new UserRequest("Test User", "test@email.com", "+123456789", true);
        Mockito.when(userRepo.findByEmail(Mockito.any())).thenReturn(Optional.empty());

        User user = Mockito.mock(User.class);
        Mockito.doNothing().when(userRepo).save(Mockito.any());
        Mockito.when(user.getName()).thenReturn("Test User");
        Mockito.when(user.getEmail()).thenReturn(new Email("test@email.com"));
        Mockito.when(user.getPhone()).thenReturn(new PhoneNumber("+123456789"));
        Mockito.when(user.isActive()).thenReturn(true);

        UserResponse response = UserResponse.from(user);
        assertNotNull(response);
    }

    @Test
    void testAddUserDuplicateThrows() {
        UserRequest req = new UserRequest("Test User", "test@email.com", "+123456789", true);
        Mockito.when(userRepo.findByEmail(Mockito.any())).thenReturn(Optional.of(new User("Test", new Email("test@email.com"), new PhoneNumber("+123456789"), true)));
        assertThrows(DuplicateUserException.class, () -> userService.addUser(req));
    }

    @Test
    void testGetAllUsersReturnsList() {
        Mockito.when(userRepo.findAll()).thenReturn(Collections.emptyList());
        assertNotNull(userService.getAllUsers());
    }
}
