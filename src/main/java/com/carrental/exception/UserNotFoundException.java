package com.carrental.exception;

public class UserNotFoundException extends CarRentalException {
    public UserNotFoundException(String email) {
        super("User with email '" + email + "' not found");
    }
}
