package com.example.webchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webchat.entity.ChatEntity;

public interface ChatingRepository extends JpaRepository<ChatEntity, Integer>{
    // 특정 채팅방의 모든 메시지를 가져오는 메서드
	List<ChatEntity> findByChatRoom_ChatRoomNo(int chatRoomNo);
}
