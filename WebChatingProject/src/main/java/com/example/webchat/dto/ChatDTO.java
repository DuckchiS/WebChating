package com.example.webchat.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDTO {
    private int chatNo;
    private String chatContent;
    private LocalDateTime chatDatetime;
    private int chatRoomNo;
}