package com.carrental.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PhoneNumber {
    private final String value;

    @JsonCreator
    public PhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }
        if (!isValid(phone)) {
            throw new IllegalArgumentException("Phone number must be in international format with country code (e.g., +1234567890)");
        }
        this.value = phone;
    }

    private boolean isValid(String phone) {
        return phone.matches("^\\+[1-9]\\d{1,14}$");
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}