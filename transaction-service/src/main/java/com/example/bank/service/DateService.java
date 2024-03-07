package com.example.bank.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class DateService {
    public Date getCurrentDate() {
        LocalDate today = LocalDate.now();
        return Date.valueOf(today);
    }
}
