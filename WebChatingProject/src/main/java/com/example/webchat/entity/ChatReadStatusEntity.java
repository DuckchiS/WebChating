package com.example.webchat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chatreadstatus")
@IdClass(ChatReadStatusId.class)
@Getter
@Setter
public class ChatReadStatusEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "chat_no", nullable = false)
    private ChatEntity chat;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private UserEntity user;

    @Column(name = "read_status", nullable = false)
    private boolean readStatus;

    @Column(name = "read_date", nullable = false)
    private LocalDateTime readDate;
}
