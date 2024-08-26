package com.example.webchat.service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.ChatEntity;
import com.example.webchat.entity.ChatReadStatusEntity;
import com.example.webchat.entity.ChatReadStatusId;
import com.example.webchat.entity.UserEntity;
import com.example.webchat.repository.ChatReadStatusRepository;

@Service
public class ReadStatusService {
    @Autowired
    private ChatReadStatusRepository chatReadStatusRepository;

    // 읽음 상태 업데이트
    public void updateReadStatus(int chatNo, int userNo, boolean readStatus) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setChatNo(chatNo);
        
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNo(userNo);

        ChatReadStatusId chatReadStatusId = new ChatReadStatusId(chatEntity, userEntity);
        Optional<ChatReadStatusEntity> optionalReadStatus = chatReadStatusRepository.findById(chatReadStatusId);
        
        if (optionalReadStatus.isPresent()) {
            ChatReadStatusEntity readStatusEntity = optionalReadStatus.get();
            readStatusEntity.setReadStatus(readStatus);
            readStatusEntity.setReadDate(LocalDateTime.now());
            chatReadStatusRepository.save(readStatusEntity);
        }
    }

    // 특정 유저의 읽지 않은 메시지 상태 조회
    public List<ChatReadStatusEntity> getUnreadMessagesByUser(int userNo) {
        return chatReadStatusRepository.findUnreadByUser(userNo);
    }
}