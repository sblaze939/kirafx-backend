package com.kirafx.kirabackend.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDeleteUserRequest {

    @NotEmpty(message = "User IDs list cannot be empty")
    @NotNull(message = "User IDs are required")
    private List<String> userIds;

}
