package com.kirafx.kirabackend.helper;

import java.util.function.Consumer;

import com.kirafx.kirabackend.utils.enums.UserRole;

public class HelperFunctions {

    private HelperFunctions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Updates a field only if the provided value is not null or empty
     *
     * @param value The new value to set
     * @param setter The setter method reference
     */
    public static void updateFieldIfPresent(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }

    /**
     * Updates a field only if the provided value is not null
     *
     * @param value The new value to set
     * @param setter The setter method reference
     */
    public static <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    /**
     * Checks if a user has permission to perform an action on a target user
     * ADMIN and SUPERADMIN can perform actions on any user Regular users can
     * only perform actions on themselves
     *
     * @param targetUserId The ID of the user being acted upon
     * @param requestingUserId The ID of the user requesting the action
     * @param role The role of the requesting user
     * @return true if the user has permission, false otherwise
     */
    public static boolean hasPermission(String targetUserId, String requestingUserId, String role) {
        if (role.equalsIgnoreCase(UserRole.ADMIN.name()) || role.equalsIgnoreCase(UserRole.SUPERADMIN.name())) {
            return true;
        }
        return targetUserId.equals(requestingUserId);
    }

    /**
     * Checks if a user has admin privileges (ADMIN or SUPERADMIN)
     *
     * @param role The role to check
     * @return true if the role is ADMIN or SUPERADMIN, false otherwise
     */
    public static boolean isAdmin(String role) {
        return role.equalsIgnoreCase(UserRole.ADMIN.name()) || role.equalsIgnoreCase(UserRole.SUPERADMIN.name());
    }

    /**
     * Generates a unique 8-character user ID
     *
     * @return a unique 8-character user ID
     */
    public static String generateUserId() {
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
