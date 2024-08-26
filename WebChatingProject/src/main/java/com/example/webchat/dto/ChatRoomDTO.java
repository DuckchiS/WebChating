package com.example.webchat.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {
    private int chatRoomNo;
    private String userNickname;
    private LocalDateTime chatRoomDatetime;
}
