package com.kirafx.kirabackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirafx.kirabackend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmailOrUserName(String userEmail, String userName);
    Optional<User> findByUserId(Long userId);

}
