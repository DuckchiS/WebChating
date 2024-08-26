package com.example.webchat.entity;

import java.io.Serializable;
import java.util.Objects;

public class ChatReadStatusId implements Serializable{
    private ChatEntity chat;  // chat 필드는 ChatEntity 타입
    private UserEntity user;  // user 필드는 UserEntity 타입

    // 기본 생성자
    public ChatReadStatusId() {}

    // 매개변수가 있는 생성자 추가
    public ChatReadStatusId(ChatEntity chat, UserEntity user) {
        this.chat = chat;
        this.user = user;
    }

    // equals 메서드 수정
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatReadStatusId that = (ChatReadStatusId) o;
        return Objects.equals(chat, that.chat) && Objects.equals(user, that.user);
    }

    // hashCode 메서드 수정
    @Override
    public int hashCode() {
        return Objects.hash(chat, user);
    }
}
