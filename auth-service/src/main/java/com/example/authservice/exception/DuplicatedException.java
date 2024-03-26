package com.example.authservice.exception;

import com.example.authservice.utils.MessagesUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicatedException extends RuntimeException {

    private String message;

    public DuplicatedException(String errorCode, Object... var2) {
        this.message = MessagesUtils.getMessage(errorCode, var2);
    }

}
