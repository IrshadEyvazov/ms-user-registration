package com.example.registration.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    INTERNAL_ERROR_OCCURRED(0, "Internal error occurred"),
    NOT_FOUND(1, "Not found"),
    CUSTOMER_ALREADY_EXIST(2, "Customer already exist");
    int code;
    String message;
}
