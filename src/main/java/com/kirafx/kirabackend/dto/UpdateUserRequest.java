package com.kirafx.kirabackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    private String userName;

    @Email(message = "Email should be valid")
    private String userEmail;

    private String password;

    private String userFirstName;

    private String userLastName;

    private String userRole;

    private String userPhone;

}
