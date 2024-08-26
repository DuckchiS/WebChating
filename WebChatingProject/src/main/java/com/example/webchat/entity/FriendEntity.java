package com.example.webchat.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "friend")
@IdClass(FriendId.class)
@Getter
@Setter
public class FriendEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_no1", nullable = false)
    private UserEntity userNo1;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_no2", nullable = false)
    private UserEntity userNo2;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    @Column(name = "requestdate", nullable = false)
    private LocalDateTime requestDate;
}
