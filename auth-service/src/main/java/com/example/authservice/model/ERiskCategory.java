package com.example.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERiskCategory {
    NETWORK_SECURITY("Network Security"), PERMISSION_MANAGEMENT("Permission Management");
    private String name;

}
