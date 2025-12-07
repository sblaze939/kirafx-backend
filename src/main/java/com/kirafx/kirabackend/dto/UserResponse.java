package com.kirafx.kirabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String userEmail;
    private String userRole;
    private String userId;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPhone;

}
