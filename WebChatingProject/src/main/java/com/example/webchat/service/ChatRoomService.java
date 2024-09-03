package com.example.webchat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.ChatRoomEntity;
import com.example.webchat.repository.ChatRoomRepository;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    public int createChatRoom(String userNickname) {
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setUserNickname(userNickname);
        ChatRoomEntity savedChatRoom = chatRoomRepository.save(chatRoom);
        return savedChatRoom.getChatRoomNo();
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
