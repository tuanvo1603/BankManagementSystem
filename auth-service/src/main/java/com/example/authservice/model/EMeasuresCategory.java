package com.example.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EMeasuresCategory {

    DATA_ENCRYPT("Data Encrypt"),

    MONITOR_SYSTEM("Monitor System");

    private final String name;

}
