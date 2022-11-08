package com.eva.tradingApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TraderNotFoundException extends RuntimeException{
    public TraderNotFoundException(String message) {
        super(message);
    }
}
