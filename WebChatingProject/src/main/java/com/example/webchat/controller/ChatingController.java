package com.example.webchat.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.example.webchat.entity.UserEntity;
import com.example.webchat.service.ChatRoomService;
import com.example.webchat.service.ChatingService;
import com.example.webchat.service.MessageService;
import com.example.webchat.service.ReadStatusService;

import jakarta.servlet.http.HttpSession;

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

    // 채팅방 목록 페이지
    @GetMapping("/chatRoomList")
    public String chatRoomList(Model model, HttpSession session) {
        // 유저 정보 가져오기
        UserEntity user = (UserEntity) session.getAttribute("loggedInUser");
        if (user != null) {
            // UserDTO 생성
            UserDTO userDTO = new UserDTO();
            userDTO.setU_nickname((user.getUserNickname()));
            model.addAttribute("loggedInUser", userDTO);
        }

        // 채팅방 목록 처리
        List<ChatRoomEntity> chatRoomEntities = chatRoomService.getAllChatRooms();
        List<ChatRoomDTO> chatRoomDTOs = chatRoomEntities.stream()
            .map(entity -> {
                ChatRoomDTO dto = new ChatRoomDTO();
                dto.setChatRoomNo(entity.getChatRoomNo());
                dto.setUserNickname(entity.getUserNickname());
                dto.setChatRoomDatetime(entity.getChatRoomDatetime());
                return dto;
            })
            .collect(Collectors.toList());
        model.addAttribute("chatRooms", chatRoomDTOs);

        return "chating/chatRoomList";
    }
    
    // 채팅방 페이지
    @GetMapping("/chatRoom/{chatRoomNo}")
    public String chatRoom(@PathVariable int chatRoomNo, Model model) {
        // 채팅방 정보를 가져와서 모델에 추가
        Optional<ChatRoomEntity> chatRoom = chatRoomService.getChatRoomById(chatRoomNo);
        if (chatRoom.isPresent()) {
            model.addAttribute("chatRoom", chatRoom.get());
            // 메시지 목록을 가져와서 모델에 추가
            List<ChatDTO> messages = chatingService.getMessagesByChatRoom(chatRoomNo);
            model.addAttribute("messages", messages);
            return "chating/chatRoom"; // chatRoom.jsp로 포워딩
        } else {
            // 채팅방이 없을 경우 에러 페이지로 포워딩
            return "error/404"; // error/404.jsp로 포워딩
        }
    }

    // 채팅방 생성은 JSON 응답으로 계속 처리
    @PostMapping("/rooms")
    @ResponseBody
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        int chatRoomNo = chatRoomService.createChatRoom(chatRoomDTO.getUserNickname());
        return ResponseEntity.ok(new ResponseMessage("Chat room created successfully", chatRoomNo));
    }

    // 유저 닉네임으로 채팅방 조회 (JSON 응답)
    @GetMapping("/rooms/user/{userNickname}")
    @ResponseBody
    public ResponseEntity<?> getChatRoomsByUser(@PathVariable String userNickname) {
        List<ChatRoomEntity> chatRooms = chatRoomService.getChatRoomsByUser(userNickname);
        return ResponseEntity.ok(chatRooms);
    }

    // 채팅 메시지 전송 (JSON 응답)
    @PostMapping("/messages")
    @ResponseBody
    public ResponseEntity<?> sendMessage(@RequestBody ChatDTO chatDTO) {
        ChatEntity message = chatingService.sendMessage(chatDTO.getChatRoomNo(), chatDTO.getChatContent(), chatDTO.getChatNo());
        return ResponseEntity.ok(new ResponseMessage("Message sent successfully", message.getChatNo()));
    }

    // 특정 채팅방의 메시지 목록 조회 (JSON 응답)
    @GetMapping("/rooms/{chatRoomNo}/messages")
    @ResponseBody
    public ResponseEntity<?> getMessagesByChatRoom(@PathVariable int chatRoomNo) {
        List<ChatDTO> messages = chatingService.getMessagesByChatRoom(chatRoomNo);
        return ResponseEntity.ok(messages);
    }

    // 채팅 메시지 읽음 상태 업데이트 (JSON 응답)
    @PostMapping("/read-status")
    @ResponseBody
    public ResponseEntity<?> updateReadStatus(@RequestBody ChatReadStatusDTO readStatusDTO) {
        readStatusService.updateReadStatus(readStatusDTO.getChatNo(), readStatusDTO.getUserNo(), readStatusDTO.isReadStatus());
        return ResponseEntity.ok(new ResponseMessage("Read status updated successfully"));
    }
}
