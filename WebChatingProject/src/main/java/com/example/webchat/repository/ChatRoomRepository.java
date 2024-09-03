package com.example.webchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webchat.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {
    // 채팅방 목록을 가져오거나 필요한 쿼리 메서드를 추가할 수 있습니다.
    List<ChatRoomEntity> findByUserNickname(String userNickname);
    
    // 모든 채팅방을 가져오는 메소드 추가
    List<ChatRoomEntity> findAll();
}
