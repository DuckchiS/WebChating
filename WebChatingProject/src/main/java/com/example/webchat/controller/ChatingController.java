package com.example.webchat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chating")
public class ChatingController {
	
    // 채팅방 생성
    @PostMapping("/rooms")
    @ResponseBody
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        int chatRoomNo = chatRoomService.createChatRoom(chatRoomDTO.getUserNickname());
        return ResponseEntity.ok(new ResponseMessage("Chat room created successfully", chatRoomNo));
    }

    // 채팅방 입장
    @PostMapping("/rooms/{chatRoomNo}/join")
    @ResponseBody
    public ResponseEntity<?> joinChatRoom(@PathVariable int chatRoomNo, @RequestBody UserDTO userDTO) {
        boolean success = chatRoomService.joinChatRoom(chatRoomNo, userDTO.getU_no());
        if (success) {
            return ResponseEntity.ok(new ResponseMessage("User joined the chat room successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Failed to join the chat room"));
        }
    }

    // 채팅 메시지 전송
    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO messageDTO) {
        messageService.sendMessage(messageDTO.getChatRoomNo(), messageDTO.getChatContent());
        return ResponseEntity.ok(new ResponseMessage("Message sent successfully"));
    }

    // 채팅 메시지 읽음 상태 업데이트
    @PostMapping("/read-status")
    @ResponseBody
    public ResponseEntity<?> updateReadStatus(@RequestBody ReadStatusDTO readStatusDTO) {
        readStatusService.updateReadStatus(readStatusDTO.getChatNo(), readStatusDTO.getUserNo(), readStatusDTO.isReadStatus());
        return ResponseEntity.ok(new ResponseMessage("Read status updated successfully"));
    }

    // 채팅 메시지 목록 조회
    @GetMapping("/rooms/{chatRoomNo}/messages")
    @ResponseBody
    public ResponseEntity<?> getMessagesByChatRoom(@PathVariable int chatRoomNo) {
        List<MessageDTO> messages = messageService.getMessagesByChatRoom(chatRoomNo);
        return ResponseEntity.ok(messages);
    }
}
