package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {

    private Movie movie;

    private Showing showing;

    @Test
    void specialMovieWith20PercentDiscount() {
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        showing = new Showing(movie, 5, LocalDateTime.of(2022, Month.MAY,07,17, 30));
        assertEquals(10, movie.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWith1stShowingDiscount() {
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        showing = new Showing(movie, 1, LocalDateTime.of(2022, Month.MAY,07,17, 30));
        assertEquals(9.5, movie.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWith2ndShowingDiscount() {
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        showing = new Showing(movie, 2, LocalDateTime.of(2022, Month.MAY,07,17, 30));
        assertEquals(10.5, movie.calculateTicketPrice(showing));
    }


    @Test
    void specialMovieWith25PercentDiscount() {
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        showing = new Showing(movie, 1, LocalDateTime.of(2022, Month.MAY,07,11, 30));
        assertEquals(9.375, movie.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWithDay7thOfMonthDiscount() {
        movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        showing = new Showing(movie, 5, LocalDateTime.of(2022, Month.MAY,07,10, 30));
        assertEquals(11.5, movie.calculateTicketPrice(showing));
    }
}
