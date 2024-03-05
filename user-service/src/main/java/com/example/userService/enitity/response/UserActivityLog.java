package com.example.userService.enitity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityLog {

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("activity_type")
    private String activityType;

    @JsonProperty("timestamp")
    private String timestamp;

}