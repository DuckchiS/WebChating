package com.example.webchat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no") 
    private int userNo;

    @Column(name = "user_id", nullable = false, unique = true, length = 50) 
    private String userId;

    @Column(name = "user_nickname", nullable = false, unique = true, length = 100)  
    private String userNickname;

    @Column(name = "user_password", nullable = false, length = 255) 
    private String userPassword;

    @Column(name = "user_name", nullable = false, length = 50) 
    private String userName;

    @Column(name = "user_phone", nullable = false, length = 25)  
    private String userPhone;

    @Column(name = "user_email", nullable = false, length = 255) 
    private String userEmail;

    @Column(name = "user_role", nullable = false, length = 50)  
    private String userRole;
}
