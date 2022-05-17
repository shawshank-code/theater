package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    private Customer customer;

    private Showing showing;

    @Test
    void totalFeeForSpecialMovieCode() {
         customer = new Customer("John Doe", "unused-id");
         showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                3,
                 LocalDateTime.of(2022, Month.MAY,07,10, 30)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 24);
    }

    @Test
    void totalFeeFor1stShowing() {
        customer = new Customer("John Doe", "unused-id");
         showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                1,
                 LocalDateTime.of(2022, Month.MAY,07,12, 30)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 21);
    }

    @Test
    void totalFeeFor2ndShowing() {
        customer = new Customer("John Doe", "unused-id");
        showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0),
                2,
                LocalDateTime.of(2022, Month.MAY,05,10, 30)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 24);
    }

    @Test
    void totalFeeForShowBetween11amAnd4pm() {
        customer = new Customer("John Doe", "unused-id");
        showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                3,
                LocalDateTime.of(2022, Month.MAY,07,12, 30)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 22.5);
    }

    @Test
    void totalFeeForShowOn7thOfMonth() {
        customer = new Customer("John Doe", "unused-id");
        showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0),
                3,
                LocalDateTime.of(2022, Month.MAY,07,17, 30)
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 27);
    }
}
