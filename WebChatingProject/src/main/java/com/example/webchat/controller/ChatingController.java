package com.example.webchat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webchat.dto.ChatDTO;
import com.example.webchat.dto.ChatReadStatusDTO;
import com.example.webchat.dto.ChatRoomDTO;
import com.example.webchat.dto.ResponseMessage;
import com.example.webchat.dto.UserDTO;
import com.example.webchat.entity.ChatEntity;
import com.example.webchat.entity.ChatRoomEntity;
import com.example.webchat.service.ChatRoomService;
import com.example.webchat.service.ChatingService;
import com.example.webchat.service.MessageService;
import com.example.webchat.service.ReadStatusService;

@Controller
@RequestMapping("/chating")
public class ChatingController {

    @Autowired
    private ChatingService chatingService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ReadStatusService readStatusService;


    // 채팅방 생성
    @PostMapping("/rooms")
    @ResponseBody
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        int chatRoomNo = chatRoomService.createChatRoom(chatRoomDTO.getUserNickname());
        return ResponseEntity.ok(new ResponseMessage("Chat room created successfully", chatRoomNo));
    }

    // 유저 닉네임으로 채팅방 조회
    @GetMapping("/rooms/user/{userNickname}")
    @ResponseBody
    public ResponseEntity<?> getChatRoomsByUser(@PathVariable String userNickname) {
        List<ChatRoomEntity> chatRooms = chatRoomService.getChatRoomsByUser(userNickname);
        return ResponseEntity.ok(chatRooms);
    }

    // 채팅방 입장
    @GetMapping("/rooms/{chatRoomNo}")
    @ResponseBody
    public ResponseEntity<?> getChatRoomById(@PathVariable int chatRoomNo) {
        Optional<ChatRoomEntity> chatRoom = chatRoomService.getChatRoomById(chatRoomNo);
        if (chatRoom.isPresent()) {
            return ResponseEntity.ok(chatRoom.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Chat room not found"));
        }
    }

    // 채팅 메시지 전송 (ChatDTO 사용)
    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<?> sendMessage(@RequestBody ChatDTO chatDTO) {
        ChatEntity message = chatingService.sendMessage(chatDTO.getChatRoomNo(), chatDTO.getChatContent(), chatDTO.getChatNo());
        return ResponseEntity.ok(new ResponseMessage("Message sent successfully", message.getChatNo()));
    }

    // 특정 채팅방의 메시지 목록 조회 (ChatDTO 리스트 반환)
    @GetMapping("/rooms/{chatRoomNo}/messages")
    @ResponseBody
    public ResponseEntity<?> getMessagesByChatRoom(@PathVariable int chatRoomNo) {
        List<ChatDTO> messages = chatingService.getMessagesByChatRoom(chatRoomNo);
        return ResponseEntity.ok(messages);
    }

    // 채팅 메시지 읽음 상태 업데이트
    @PostMapping("/read-status")
    @ResponseBody
    public ResponseEntity<?> updateReadStatus(@RequestBody ChatReadStatusDTO readStatusDTO) {
        readStatusService.updateReadStatus(readStatusDTO.getChatNo(), readStatusDTO.getUserNo(), readStatusDTO.isReadStatus());
        return ResponseEntity.ok(new ResponseMessage("Read status updated successfully"));
    }
}
