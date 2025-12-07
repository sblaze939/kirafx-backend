package com.kirafx.kirabackend.entities;

import java.time.LocalDateTime;

import com.kirafx.kirabackend.helper.HelperFunctions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", length = 8)
    private String userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_role", nullable = false, unique = false)
    private String userRole;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "user_first_name", nullable = false, unique = false)
    private String userFirstName;

    @Column(name = "user_last_name", nullable = true, unique = false)
    private String userLastName;

    @Column(name = "user_phone", nullable = true, unique = true)
    private String userPhone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (userId == null) {
            userId = HelperFunctions.generateUserId();
        }
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
