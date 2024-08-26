package com.example.webchat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
    private String message;
    private Object data;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}