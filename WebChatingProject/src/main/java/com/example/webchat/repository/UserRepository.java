package com.example.webchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webchat.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    boolean existsByUserId(String userId);  
    boolean existsByUserNickname(String userNickname); 
    UserEntity findByUserId(String userId);  
}
