package com.example.manageUser.enitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DateTimeZone {
    @JsonProperty("year")
    private int year;
    @JsonProperty("month")
    private int month;
    @JsonProperty("day")
    private int day;
    @JsonProperty("hour")
    private int hour;
    @JsonProperty("minute")
    private int minute;
    @JsonProperty("seconds")
    private int seconds;
    @JsonProperty("milliSeconds")
    private int milliSeconds;
    @JsonProperty("dateTime")
    private String dateTime;
    @JsonProperty("date")
    private String date;
    @JsonProperty("time")
    private String time;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("dayOfWeek")
    private String dayOfWeek;
    @JsonProperty("dstActive")
    private boolean dstActive;
}
