package com.example.webchat.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatReadStatusDTO {
    private int chatNo;
    private int userNo;
    private boolean readStatus;
    private LocalDateTime readDate;
}