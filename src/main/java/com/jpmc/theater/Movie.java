package com.jpmc.theater;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Movie {
    private static final int MOVIE_CODE_SPECIAL = 1;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public String getRunningTime() {
        long hour = runningTime.toHours();
        long remainingMin = runningTime.toMinutes() - TimeUnit.HOURS.toMinutes(runningTime.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }
    private String handlePlural(long value) {
        return value == 1 ? "" : "s";
    }
    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) {
        double specialDiscount = 0;
        double seventhDiscount = 0;
        double timeDiscount = 0;
        int showSequence = showing.getSequenceOfTheDay();
        LocalDateTime showStartTime = showing.getStartTime();
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if(showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        }
        if(showStartTime!=null){
            if(showStartTime.getDayOfMonth() == 7){
                seventhDiscount = 1; //$1 discount for 7th day of the month
            }
            if (showStartTime.toLocalTime().isAfter(LocalTime.parse("11:00:00"))
                    && showStartTime.toLocalTime().isBefore(LocalTime.parse("16:00:00"))) {
                timeDiscount = ticketPrice * 0.25; //25% discount for shows between 11 AM - 4 PM
            }
        }
        // biggest discount wins
        return Collections.max(Arrays.asList(specialDiscount, sequenceDiscount, seventhDiscount, timeDiscount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}