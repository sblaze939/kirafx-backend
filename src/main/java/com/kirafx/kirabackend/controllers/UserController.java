package com.kirafx.kirabackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kirafx.kirabackend.dto.ApiResponseGenerator;
import com.kirafx.kirabackend.dto.AuthResponse;
import com.kirafx.kirabackend.dto.BatchDeleteUserRequest;
import com.kirafx.kirabackend.dto.DeleteUserRequest;
import com.kirafx.kirabackend.dto.UpdateUserRequest;
import com.kirafx.kirabackend.dto.UserResponse;
import com.kirafx.kirabackend.services.UserService;
import static com.kirafx.kirabackend.utils.constants.AppMessages.GET_USERS_SUCCESS;
import static com.kirafx.kirabackend.utils.constants.AppMessages.GET_USER_SUCCESS;
import static com.kirafx.kirabackend.utils.constants.AppMessages.USERS_BATCH_DELETED_SUCCESS;
import static com.kirafx.kirabackend.utils.constants.AppMessages.USER_DELETED_SUCCESS;
import static com.kirafx.kirabackend.utils.constants.AppMessages.USER_UPDATED_SUCCESS;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ApiResponseGenerator<List<UserResponse>> getAllUsers(@RequestParam(name = "userRole", required = false) String role) {
        List<UserResponse> users = userService.getAllUsers(role);
        return ApiResponseGenerator.success(GET_USERS_SUCCESS, users);
    }

    @GetMapping("/{userId}")
    public ApiResponseGenerator<UserResponse> getUserById(@PathVariable String userId) {
        UserResponse user = userService.getUserById(userId);
        return ApiResponseGenerator.success(GET_USER_SUCCESS, user);
    }

    @PutMapping("/update/{userId}")
    public ApiResponseGenerator<AuthResponse> update(@RequestBody UpdateUserRequest req, @PathVariable String userId) {
        AuthResponse authResponse = userService.update(req, userId);
        return ApiResponseGenerator.success(USER_UPDATED_SUCCESS, authResponse);
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponseGenerator<Void> delete(@RequestBody DeleteUserRequest req, @PathVariable String userId) {
        userService.delete(req, userId);
        return ApiResponseGenerator.success(USER_DELETED_SUCCESS, null);
    }

    @DeleteMapping("/deleteAll/{userId}")
    public ApiResponseGenerator<Void> batchDelete(@RequestBody BatchDeleteUserRequest req, @PathVariable String userId) {
        userService.batchDelete(req, userId);
        return ApiResponseGenerator.success(USERS_BATCH_DELETED_SUCCESS, null);
    }
}
