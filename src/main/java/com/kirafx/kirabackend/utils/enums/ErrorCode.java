package com.kirafx.kirabackend.utils.enums;

public enum ErrorCode {

    // User related errors
    USER_NOT_FOUND(404, "User not found"),
    INVALID_CREDENTIALS(401, "Invalid login credentials"),
    ACCOUNT_LOCKED(423, "Your account is locked"),
    // Token related errors
    TOKEN_EXPIRED(498, "Token expired"),
    TOKEN_INVALID(499, "Invalid token"),
    // Server errors
    INTERNAL_ERROR(500, "Something went wrong");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
