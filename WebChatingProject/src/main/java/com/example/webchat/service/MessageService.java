package com.example.webchat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.ChatEntity;
import com.example.webchat.repository.ChatingRepository;

@Service
public class MessageService {
    @Autowired
    private ChatingRepository chatingRepository;

    // 메시지 전송
    public ChatEntity sendMessage(int chatRoomNo, String chatContent) {
    	ChatEntity message = new ChatEntity();
        message.setChatRoomNo(chatRoomNo);
        message.setChatContent(chatContent);
        return chatingRepository.save(message);
    }

    // 특정 채팅방의 메시지 가져오기
    public List<ChatEntity> getMessagesByChatRoom(int chatRoomNo) {
        return chatingRepository.findByChatRoomNo(chatRoomNo);
    }
}
