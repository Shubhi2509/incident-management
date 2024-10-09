package com.assignment.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Api error.
 */
@Data
public class ApiError {

    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;
    private List<String> subErrors;

    /**
     * Instantiates a new Api error.
     */
    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    /**
     * Instantiates a new Api error.
     *
     * @param error      the error
     * @param statusCode the status code
     */
    public ApiError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }

    /**
     * Instantiates a new Api error.
     *
     * @param message the message
     * @param errors  the errors
     */
    public ApiError(String message, List<String> errors) {
        this();
        this.subErrors = errors;
        this.error = message;
    }
}
