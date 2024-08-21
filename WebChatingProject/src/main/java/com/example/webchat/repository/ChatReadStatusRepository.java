package com.example.webchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webchat.entity.ChatReadStatusEntity;
import com.example.webchat.entity.ChatReadStatusId;

public interface ChatReadStatusRepository extends JpaRepository<ChatReadStatusEntity, ChatReadStatusId>{
    // 특정 유저의 읽지 않은 메시지 상태 조회 (유저 ID 기준)
    List<ChatReadStatusEntity> findUnreadByUser(int userNo);

    // 특정 채팅방에서 유저의 읽지 않은 메시지 조회 (채팅방 ID와 유저 ID 기준)
    List<ChatReadStatusEntity> findUnreadInRoomByUser(int chatRoomNo, int userNo);
}
