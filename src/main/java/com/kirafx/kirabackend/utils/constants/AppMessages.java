package com.kirafx.kirabackend.utils.constants;

public final class AppMessages {

    private AppMessages() {
    }

    // Success Messages
    public static final String USER_REGISTERED_SUCCESS = "User registered successfully.";
    public static final String USER_LOGGED_IN_SUCCESS = "User logged in successfully.";
    public static final String GET_USERS_SUCCESS = "Users data fetched successfully.";
    public static final String GET_USER_SUCCESS = "User details fetched successfully.";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully.";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully.";
    public static final String USERS_BATCH_DELETED_SUCCESS = "Users deleted successfully.";

    // Error Messages
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String UNAUTHORIZED_ACCESS = "You don't have permission to perform this action.";
    public static final String INVALID_CREDENTIALS = "Invalid username or password.";
    public static final String ACCOUNT_DISABLED = "Your account is disabled.";
    public static final String TOKEN_INVALID = "Invalid or expired token.";

    // Validation
    public static final String EMAIL_INVALID = "Please enter a valid email.";
    public static final String PASSWORD_WEAK = "Password does not meet required strength.";
}
