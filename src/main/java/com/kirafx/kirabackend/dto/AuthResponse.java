package com.kirafx.kirabackend.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private String token;
    private String userEmail;
    private String userRole;
    private Long userId;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPhone;

}
