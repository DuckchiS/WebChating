package com.example.webchat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chating")
@Getter
@Setter
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_no")
    private int chatNo;

    @Column(name = "chat_content", nullable = true)
    private String chatContent;

    @Column(name = "chat_datetime", nullable = false)
    private LocalDateTime chatDatetime;

    @ManyToOne
    @JoinColumn(name = "chat_roomno", nullable = false)
    private ChatRoomEntity chatRoom;
}
