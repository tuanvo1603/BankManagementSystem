package com.example.bank.response;

import lombok.NoArgsConstructor;

public class DrawMoneyResponse extends ApiResponse{

    public DrawMoneyResponse(int code, String message) {
        super(code, message);
    }
}
