package com.example.webchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webchat.dto.UserDTO;
import com.example.webchat.entity.UserEntity;
import com.example.webchat.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 처리
    public void signUpProcess(UserDTO user) {
        // 비밀번호가 null이거나 빈 문자열인지 확인
        if (user.getU_pw() == null || user.getU_pw().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // 중복된 사용자 ID나 닉네임이 있는지 확인
        if (userRepository.existsByUserId(user.getU_id()) || userRepository.existsByUserNickname(user.getU_nickname())) {
            throw new IllegalArgumentException("User ID or Nickname already exists");
        }
        
        UserEntity data = new UserEntity();
        
        data.setUserId(user.getU_id());
        data.setUserNickname(user.getU_nickname());
        data.setUserPassword(bCryptPasswordEncoder.encode(user.getU_pw())); 
        data.setUserEmail(user.getU_email());
        data.setUserPhone(user.getU_phone());
        data.setUserName(user.getU_name());
        data.setUserRole("USER");
        userRepository.save(data);
    }

    // 로그인 처리
    public UserEntity findByUsername(String userId) {
        return userRepository.findByUserId(userId);
    }
}
