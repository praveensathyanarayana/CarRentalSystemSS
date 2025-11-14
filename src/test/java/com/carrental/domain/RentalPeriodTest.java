package com.carrental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RentalPeriodTest {
    private LocalDateTime start;
    private LocalDateTime end;
    private RentalPeriod rentalPeriod;

    @BeforeEach
    void setUp() {
        start = LocalDateTime.now().plusDays(1);
        end = start.plusDays(3);
        rentalPeriod = new RentalPeriod(start, end);
    }

    @Test
    void testRentalPeriodInitialization() {
        assertEquals(start, rentalPeriod.getStartDateTime());
        assertEquals(end, rentalPeriod.getEndDateTime());
    }

    @Test
    void testGetDaysMinimumOne() {
        RentalPeriod shortPeriod = new RentalPeriod(start, start.plusHours(2));
        assertEquals(1, shortPeriod.getDays());
    }

    @Test
    void testGetDaysMultiDay() {
        assertEquals(3, rentalPeriod.getDays());
    }

    @Test
    void testIsValidTrue() {
        assertTrue(rentalPeriod.isValid());
    }

    @Test
    void testIsValidFalseStartInPast() {
        RentalPeriod invalid = new RentalPeriod(LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(2));
        assertFalse(invalid.isValid());
    }

    @Test
    void testIsValidFalseEndBeforeStart() {
        RentalPeriod invalid = new RentalPeriod(start, start.minusDays(1));
        assertFalse(invalid.isValid());
    }

    @Test
    void testValidateThrowsNullsAndOrder() {
        RentalPeriod nullStart = new RentalPeriod(null, end);
        RentalPeriod nullEnd = new RentalPeriod(start, null);
        RentalPeriod wrongOrder = new RentalPeriod(end, start);

        assertThrows(IllegalArgumentException.class, nullStart::validate);
        assertThrows(IllegalArgumentException.class, nullEnd::validate);
        assertThrows(IllegalArgumentException.class, wrongOrder::validate);
    }

    @Test
    void testOverlapsWith() {
        RentalPeriod period1 = new RentalPeriod(start, end);
        RentalPeriod period2 = new RentalPeriod(start.plusDays(1), end.plusDays(1));
        RentalPeriod period3 = new RentalPeriod(end.plusDays(1), end.plusDays(3));
        assertTrue(period1.overlapsWith(period2));
        assertFalse(period1.overlapsWith(period3));
    }
}
