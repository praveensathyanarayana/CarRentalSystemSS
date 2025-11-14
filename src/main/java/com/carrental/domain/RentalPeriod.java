package com.carrental.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalPeriod {
    @NotNull(message = "Start date time is required")
    private LocalDateTime startDateTime;

    @NotNull(message = "End date time is required")
    private LocalDateTime endDateTime;

    public boolean overlapsWith(RentalPeriod other) {
        return !this.endDateTime.isBefore(other.startDateTime)
                && !this.startDateTime.isAfter(other.endDateTime);
    }

    public long getDays() {
        long days = Duration.between(startDateTime, endDateTime).toDays();
        return Math.max(days, 1);
    }

    public boolean isValid() {
        return startDateTime != null
                && endDateTime != null
                && !startDateTime.isBefore(LocalDateTime.now())
                && endDateTime.isAfter(startDateTime);
    }

    public void validate() {
        if (startDateTime == null) {
            throw new IllegalArgumentException("Start date time is required");
        }
        if (endDateTime == null) {
            throw new IllegalArgumentException("End date time is required");
        }
        if (startDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date time cannot be in the past");
        }
        if (endDateTime.isBefore(startDateTime) || endDateTime.equals(startDateTime)) {
            throw new IllegalArgumentException("End date time must be after start date time");
        }
    }
}
