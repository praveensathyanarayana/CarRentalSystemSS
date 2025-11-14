package com.carrental.exception;

public class DuplicateUserException extends CarRentalException {
    public DuplicateUserException(String email) {
        super("User with email '" + email + "' already exists");
    }
}
