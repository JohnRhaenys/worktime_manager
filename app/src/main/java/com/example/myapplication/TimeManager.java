package com.example.myapplication;

public class TimeManager {
    public static Time convertSecondsToTime(int seconds) {
        int hours = (seconds / 60) / 60;
        int minutes = (seconds / 60) % 60;
        return new Time(hours, minutes);
    }
}
