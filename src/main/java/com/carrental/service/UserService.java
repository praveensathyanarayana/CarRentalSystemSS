package com.carrental.service;

import com.carrental.domain.User;
import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.PhoneNumber;
import com.carrental.dto.UserRequest;
import com.carrental.dto.UserResponse;
import com.carrental.exception.DuplicateUserException;
import com.carrental.exception.UserNotFoundException;
import com.carrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public synchronized UserResponse addUser(UserRequest request) {
        Email email = new Email(request.email());

        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateUserException(email.value());
        }

        PhoneNumber phone = new PhoneNumber(request.phone());
        boolean active = request.active() != null ? request.active() : true;

        User user = new User(request.name(), email, phone, active);
        userRepository.save(user);

        return UserResponse.from(user);
    }

    public synchronized UserResponse getUserByEmail(String emailString) {
        Email email = new Email(emailString);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email.value()));
        return UserResponse.from(user);
    }

    public synchronized UserResponse updateUser(UserRequest request) {
        Email email = new Email(request.email());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email.value()));

        PhoneNumber phone = new PhoneNumber(request.phone());
        user.updateDetails(request.name(), phone);

        if (request.active() != null) {
            user.updateActive(request.active());
        }

        userRepository.save(user);
        return UserResponse.from(user);
    }

    public synchronized void disableUser(String emailString) {
        Email email = new Email(emailString);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email.value()));

        user.disable();
        userRepository.save(user);
    }

    public synchronized List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}