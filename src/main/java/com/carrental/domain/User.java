package com.carrental.domain;

import com.carrental.domain.vo.Email;
import com.carrental.domain.vo.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    private Email email;

    @NotNull(message = "Phone number is required")
    private PhoneNumber phone;

    private boolean active = true;

    public void disable() {
        this.active = false;
    }

    public void enable() {
        this.active = true;
    }

    public void updateDetails(String name, PhoneNumber phone) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (phone == null) {
            throw new IllegalArgumentException("Phone is required");
        }
        this.name = name;
        this.phone = phone;
    }

    public void updateActive(boolean active) {
        this.active = active;
    }
}
