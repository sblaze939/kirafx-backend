package com.kirafx.kirabackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirafx.kirabackend.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserEmailOrUserName(String userEmail, String userName);

    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserPhone(String userPhone);

    Optional<User> findByUserId(String userId);

}
