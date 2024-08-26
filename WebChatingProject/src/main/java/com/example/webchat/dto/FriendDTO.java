package com.example.webchat.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDTO {
    private int userNo1;
    private int userNo2;
    private String status; // 'pending', 'accepted', 'rejected'
    private LocalDateTime requestDate;
}