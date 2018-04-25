package com.example.blog.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CommentService {

    public String thisMoment() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[today.getMonthValue()-1] + " " + today.getDayOfMonth() + ", " + today.getYear() + " " + now.getHour() + ":" + now.getMinute();
    }
}
