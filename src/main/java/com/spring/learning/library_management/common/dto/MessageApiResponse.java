package com.spring.learning.library_management.common.dto;

public class MessageApiResponse {

    private String code;
    private String text;

    public MessageApiResponse(String code) {
        this.code = code;
    }

    public MessageApiResponse(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static MessageApiResponse build(String code, String text) {
        return new MessageApiResponse(code, text);
    }
}
