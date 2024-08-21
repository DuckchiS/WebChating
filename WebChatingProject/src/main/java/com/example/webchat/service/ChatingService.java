package com.example.webchat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.ChatEntity;
import com.example.webchat.entity.ChatReadStatusEntity;
import com.example.webchat.entity.ChatRoomEntity;
import com.example.webchat.entity.UserEntity;
import com.example.webchat.repository.ChatReadStatusRepository;
import com.example.webchat.repository.ChatingRepository;

@Service
public class ChatingService {
    @Autowired
    private ChatingRepository chatingRepository;

    @Autowired
    private ChatReadStatusRepository chatReadStatusRepository;

    // 채팅 메시지 전송
    public ChatEntity sendMessage(int chatRoomNo, String chatContent, int userNo) {
        // 새로운 채팅 메시지 엔티티 생성
        ChatEntity message = new ChatEntity();
        
        // ChatRoomEntity 설정 (여기서는 채팅방 레포지토리를 사용해 채팅방 엔티티를 찾아서 넣어줘야 합니다)
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setChatRoomNo(chatRoomNo);
        message.setChatRoom(chatRoom);
        
        // 채팅 내용 및 생성 시간 설정
        message.setChatContent(chatContent);
        message.setChatDatetime(LocalDateTime.now());
        
        // 메시지 저장
        chatingRepository.save(message);
        
        // 읽음 상태도 함께 저장
        ChatReadStatusEntity readStatus = new ChatReadStatusEntity();
        readStatus.setChat(message);
        
        // UserEntity 설정 (여기서는 사용자 엔티티를 찾아서 넣어줘야 합니다)
        UserEntity user = new UserEntity();
        user.setUserNo(userNo);
        readStatus.setUser(user);
        
        // 읽음 상태 및 읽은 날짜 설정
        readStatus.setReadStatus(false); // 전송 시 읽지 않은 상태로 설정
        readStatus.setReadDate(LocalDateTime.now());
        
        // 읽음 상태 저장
        chatReadStatusRepository.save(readStatus);

        return message;
    }

    // 특정 채팅방의 모든 메시지 조회
    public List<ChatEntity> getMessagesByChatRoom(int chatRoomNo) {
        return chatingRepository.findByChatRoom_ChatRoomNo(chatRoomNo);
    }
}
