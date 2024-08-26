package com.example.webchat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.webchat.entity.ChatReadStatusEntity;
import com.example.webchat.entity.ChatReadStatusId;

public interface ChatReadStatusRepository extends JpaRepository<ChatReadStatusEntity, ChatReadStatusId>{
    // 특정 유저의 읽지 않은 메시지 상태 조회 (유저 ID 기준)
    @Query("SELECT cr FROM ChatReadStatusEntity cr WHERE cr.user.userNo = :userNo AND cr.readStatus = false")
    List<ChatReadStatusEntity> findUnreadByUser(@Param("userNo") int userNo);

    @Query("SELECT cr FROM ChatReadStatusEntity cr WHERE cr.chat.chatRoom.chatRoomNo = :chatRoomNo AND cr.user.userNo = :userNo AND cr.readStatus = false")
    List<ChatReadStatusEntity> findUnreadInRoomByUser(@Param("chatRoomNo") int chatRoomNo, @Param("userNo") int userNo);
}
