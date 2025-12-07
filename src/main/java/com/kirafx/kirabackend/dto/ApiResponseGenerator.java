package com.kirafx.kirabackend.dto;

public class ApiResponseGenerator<T> {

    private int statusCode;
    private String message;
    private T data;

    public ApiResponseGenerator() {
    }

    public ApiResponseGenerator(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // Static helper methods for cleaner code
    public static <T> ApiResponseGenerator<T> success(String message, T data) {
        return new ApiResponseGenerator<>(200, message, data);
    }

    public static <T> ApiResponseGenerator<T> success(String message) {
        return new ApiResponseGenerator<>(200, message, null);
    }

    public static <T> ApiResponseGenerator<T> created(String message, T data) {
        return new ApiResponseGenerator<>(201, message, data);
    }

    public static <T> ApiResponseGenerator<T> created(String message) {
        return new ApiResponseGenerator<>(201, message, null);
    }

    public static <T> ApiResponseGenerator<T> error(int statusCode, String message) {
        return new ApiResponseGenerator<>(statusCode, message, null);
    }

    // Getters and setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
