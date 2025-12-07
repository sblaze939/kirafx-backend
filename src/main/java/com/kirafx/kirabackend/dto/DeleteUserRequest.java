package com.kirafx.kirabackend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequest {

    @NotEmpty(message = "User ID list cannot be empty")
    @NotNull(message = "User ID are required")
    private String userId;

}
