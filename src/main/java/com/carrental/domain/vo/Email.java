package com.carrental.domain.vo;

public record Email(String value) {
    public Email(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value.toLowerCase();
    }

    private boolean isValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @Override
    public String toString() {
        return value;
    }
}