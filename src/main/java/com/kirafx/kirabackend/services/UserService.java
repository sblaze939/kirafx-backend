package com.kirafx.kirabackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kirafx.kirabackend.dto.AuthResponse;
import com.kirafx.kirabackend.dto.BatchDeleteUserRequest;
import com.kirafx.kirabackend.dto.DeleteUserRequest;
import com.kirafx.kirabackend.dto.UpdateUserRequest;
import com.kirafx.kirabackend.dto.UserResponse;
import com.kirafx.kirabackend.entities.User;
import com.kirafx.kirabackend.exception.ResourceNotFoundException;
import com.kirafx.kirabackend.exception.UnauthorizedAccessException;
import com.kirafx.kirabackend.helper.HelperFunctions;
import com.kirafx.kirabackend.repositories.UserRepository;
import static com.kirafx.kirabackend.utils.constants.AppMessages.UNAUTHORIZED_ACCESS;
import static com.kirafx.kirabackend.utils.constants.AppMessages.USER_NOT_FOUND;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public UserResponse getUserById(String userId) {
        User user = userRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        UserResponse res = new UserResponse();
        res.setUserId(user.getUserId());
        res.setUserEmail(user.getUserEmail());
        res.setUserName(user.getUserName());
        res.setUserFirstName(user.getUserFirstName());
        res.setUserLastName(user.getUserLastName());
        res.setUserPhone(user.getUserPhone());
        res.setUserRole(user.getUserRole());

        return res;
    }

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserRole(),
                user.getUserFirstName(),
                user.getUserLastName(),
                user.getUserPhone()
        ))
                .toList();
    }

    public AuthResponse update(UpdateUserRequest req, String requestingUserId) {

        User user = userRepo.findByUserId(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        User requestingUser = userRepo.findByUserId(requestingUserId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        // Validate requesting user has permission to update
        if (!HelperFunctions.isAdmin(requestingUser.getUserRole())
                && req.getUserRole() != null && !req.getUserRole().equals(user.getUserRole())) {

            throw new UnauthorizedAccessException("You don't have permission to update the role");
        }
        if (!HelperFunctions.hasPermission(req.getUserId(), requestingUserId, requestingUser.getUserRole())) {
            throw new UnauthorizedAccessException("You don't have permission to update this user");
        }

        // Update only fields that are not null
        HelperFunctions.updateFieldIfPresent(req.getUserEmail(), user::setUserEmail);
        HelperFunctions.updateFieldIfPresent(req.getUserName(), user::setUserName);
        HelperFunctions.updateFieldIfPresent(req.getUserFirstName(), user::setUserFirstName);
        HelperFunctions.updateFieldIfPresent(req.getUserLastName(), user::setUserLastName);
        HelperFunctions.updateFieldIfPresent(req.getUserPhone(), user::setUserPhone);
        HelperFunctions.updateFieldIfPresent(req.getUserRole(), user::setUserRole);
        HelperFunctions.updateFieldIfPresent(req.getPassword(), pwd -> user.setPassword(encoder.encode(pwd)));

        userRepo.save(user);

        String token = jwtService.generateToken(user.getUserEmail());

        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setUserEmail(user.getUserEmail());
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setUserFirstName(user.getUserFirstName());
        res.setUserLastName(user.getUserLastName());
        res.setUserPhone(user.getUserPhone());
        res.setUserRole(user.getUserRole());

        return res;
    }

    public void delete(DeleteUserRequest req, String requestingUserId) {

        User user = userRepo.findByUserId(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        User requestingUser = userRepo.findByUserId(requestingUserId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        // Validate requesting user has permission to delete
        if (!HelperFunctions.hasPermission(user.getUserId(), requestingUserId, requestingUser.getUserRole())) {
            throw new UnauthorizedAccessException("You don't have permission to delete this user");
        }

        userRepo.deleteById(user.getUserId());

    }

    public void batchDelete(BatchDeleteUserRequest req, String requestingUserId) {

        User requestingUser = userRepo.findByUserId(requestingUserId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        // Validate requesting user has permission to delete
        if (!HelperFunctions.isAdmin(requestingUser.getUserRole())) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACCESS);
        }

        // Check each user exists
        for (String targetUserId : req.getUserIds()) {
            if (!userRepo.findById(targetUserId).isPresent()) {
                throw new ResourceNotFoundException("User with ID " + targetUserId + " not found");
            }
        }

        userRepo.deleteAllById(req.getUserIds());

    }
}
