package com.kirafx.kirabackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kirafx.kirabackend.dto.AuthResponse;
import com.kirafx.kirabackend.dto.LoginRequest;
import com.kirafx.kirabackend.dto.RegisterRequest;
import com.kirafx.kirabackend.entities.User;
import com.kirafx.kirabackend.exception.InvalidCredentialsException;
import com.kirafx.kirabackend.exception.ResourceAlreadyExistsException;
import com.kirafx.kirabackend.repositories.UserRepository;
import com.kirafx.kirabackend.utils.enums.UserRole;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest req) {

        if (userRepo.findByUserEmailOrUserName(req.getUserEmail(), req.getUserName()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "User with email '" + req.getUserEmail() + "' or username '" + req.getUserName() + "' already exists"
            );
        }

        User user = new User();
        user.setUserEmail(req.getUserEmail());
        user.setUserName(req.getUserName());
        user.setUserFirstName(req.getUserFirstName());
        user.setUserLastName(req.getUserLastName());
        user.setUserPhone(req.getUserPhone());
        user.setUserRole(UserRole.USER.toString());
        user.setPassword(encoder.encode(req.getPassword()));

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

        return res;
    }

    public AuthResponse login(LoginRequest req) {

        User user = userRepo
                .findByUserEmailOrUserName(req.getLogin(), req.getLogin())
                .orElseThrow(() -> new InvalidCredentialsException("Incorrect login for user: " + req.getLogin()));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password for user: " + req.getLogin());
        }

        String token = jwtService.generateToken(user.getUserEmail());

        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setUserEmail(user.getUserEmail());
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setUserFirstName(user.getUserFirstName());
        res.setUserLastName(user.getUserLastName());
        res.setUserPhone(user.getUserPhone());

        return res;
    }

}
