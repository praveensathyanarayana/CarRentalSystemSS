package com.carrental.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Phone number must be in international format with country code")
        String phone,

        Boolean active
) {
}
