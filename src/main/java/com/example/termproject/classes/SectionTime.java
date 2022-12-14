package com.example.termproject.classes;

public class SectionTime {
    int startHour;
    int startMinutes;
    int endHour;
    int endMinutes;

    SectionTime(String text) {
        String[] times = text.split("-");
        String[] starts = times[0].split(":");
        String[] ends = times[0].split(":");
    }
}
