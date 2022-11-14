package com.example.myapplication;

import androidx.annotation.NonNull;

public class Time {
    private final int hours;
    private final int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getTotalSeconds() {
        return this.hours * 3600 + this.minutes * 60;
    }

    // Adds the current time to another time, returning the total result in Time
    public Time plus(Time time) {
        int seconds = getTotalSeconds() + time.getTotalSeconds();
        return TimeManager.convertSecondsToTime(seconds);
    }

    // Subtracts the current time from another time, returning the total result in Time
    public Time minus(Time time) {
        int seconds = getTotalSeconds() - time.getTotalSeconds();
        return TimeManager.convertSecondsToTime(seconds);
    }

    @NonNull
    @Override
    public String toString() {
        return "Time{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}
