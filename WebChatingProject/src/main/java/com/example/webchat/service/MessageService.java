package com.example.webchat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.ChatEntity;
import com.example.webchat.entity.ChatRoomEntity;
import com.example.webchat.repository.ChatingRepository;

@Service
public class MessageService {
    @Autowired
    private ChatingRepository chatingRepository;

    // 메시지 전송
    public ChatEntity sendMessage(int chatRoomNo, String chatContent) {
        ChatEntity message = new ChatEntity();
        // ChatRoomEntity 설정
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setChatRoomNo(chatRoomNo);
        message.setChatRoom(chatRoom);
        
        // 채팅 내용 설정
        message.setChatContent(chatContent);
        message.setChatDatetime(LocalDateTime.now());
        
        return chatingRepository.save(message);
    }

    // 특정 채팅방의 메시지 가져오기
    public List<ChatEntity> getMessagesByChatRoom(int chatRoomNo) {
        return chatingRepository.findByChatRoom_ChatRoomNo(chatRoomNo);
    }
}
