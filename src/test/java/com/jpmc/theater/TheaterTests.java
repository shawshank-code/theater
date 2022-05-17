package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TheaterTests {
    @Test
    void totalFeeForCustomerForSpecialMovie() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 40);
    }

    @Test
    void totalFeeForCustomerFor1stShow() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 1, 4);
        assertEquals(reservation.totalFee(), 32);
    }

    @Test
    void totalFeeForCustomerFor2ndShow() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 40);
    }

    @Test
    void totalFeeForCustomerForShowingBetween11And4() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 3, 4);
        assertEquals(reservation.totalFee(), 27);
    }

    @Test
    void printMovieScheduleFormats() throws JsonProcessingException {
        Theater theater = mock(Theater.class);
        theater.printScheduleInSimpleTextFormat();
        theater.printScheduleInJsonFormat();
        verify(theater, Mockito.times(1)).printScheduleInSimpleTextFormat();
        verify(theater, Mockito.times(1)).printScheduleInJsonFormat();
    }
}
