package com.kirafx.kirabackend.utils.constants;

public final class AppMessages {

    private AppMessages() {
    }

    // Success Messages
    public static final String USER_REGISTERED_SUCCESS = "User registered successfully.";
    public static final String USER_LOGGED_IN_SUCCESS = "User logged in successfully.";

    // Error Messages
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String INVALID_CREDENTIALS = "Invalid username or password.";
    public static final String ACCOUNT_DISABLED = "Your account is disabled.";
    public static final String TOKEN_INVALID = "Invalid or expired token.";

    // Validation
    public static final String EMAIL_INVALID = "Please enter a valid email.";
    public static final String PASSWORD_WEAK = "Password does not meet required strength.";
}
