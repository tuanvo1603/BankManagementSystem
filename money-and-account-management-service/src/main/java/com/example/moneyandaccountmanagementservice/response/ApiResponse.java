package com.example.moneyandaccountmanagementservice.response;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Data
public class ApiResponse {

    private int code;

    private String message;
}
