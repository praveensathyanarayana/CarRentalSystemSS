package com.carrental.dto;

import com.carrental.domain.User;

public record UserResponse(
        String name,
        String email,
        String phone,
        boolean active
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail().value(),
                user.getPhone().getValue(),
                user.isActive()
        );
    }
}
