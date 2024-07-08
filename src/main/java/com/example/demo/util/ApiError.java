package com.example.demo.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {

    private int statusCode;

    private String message;


    public static ApiError from(int statusCode, String message){
        return ApiError.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}
