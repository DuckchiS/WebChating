package com.example.webchat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.ChatRoomEntity;
import com.example.webchat.repository.ChatRoomRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    @Transactional // 트랜잭션 어노테이션 추가
    public int createChatRoom(String userNickname) {
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setUserNickname(userNickname);
        chatRoom.setChatRoomDatetime(LocalDateTime.now()); // 채팅방 생성 시간 설정
        ChatRoomEntity savedChatRoom = chatRoomRepository.save(chatRoom); // 채팅방 저장
        return savedChatRoom.getChatRoomNo(); // 생성된 채팅방 번호 반환
    }
    
    // 유저 닉네임으로 채팅방 조회
    public List<ChatRoomEntity> getChatRoomsByUser(String userNickname) {
        return chatRoomRepository.findByUserNickname(userNickname);
    }

    // 특정 채팅방 조회
    public Optional<ChatRoomEntity> getChatRoomById(int chatRoomNo) {
        return chatRoomRepository.findById(chatRoomNo);
    }

    // 모든 채팅방 조회
    public List<ChatRoomEntity> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }
}
