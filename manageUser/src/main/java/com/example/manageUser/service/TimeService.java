package com.example.manageUser.service;

import com.example.manageUser.enitity.DateTimeZone;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeService {

    @Value("${timezone}")
     String timezone;
    private String API_URL = "https://timeapi.io/api/Time/current/zone?timeZone=";
    RestTemplate restTemplate = new RestTemplate();

    public DateTimeZone getCurrentTime() throws JsonProcessingException {
        String linkGetTimeZone = API_URL + timezone;
        String response = restTemplate.getForObject(linkGetTimeZone, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        DateTimeZone dateTimeZone = objectMapper.readValue(response, DateTimeZone.class);
        return dateTimeZone;
    }

    public Date convertToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date date = dateFormat.parse(dateString);
        return date;

    }


}
