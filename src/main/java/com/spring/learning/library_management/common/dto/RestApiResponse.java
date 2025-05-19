package com.spring.learning.library_management.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RestApiResponse<T> {

    private boolean success;
    private MessageApiResponse message;
    private String requestId;
    private T data;

    public RestApiResponse(boolean success, MessageApiResponse message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    private RestApiResponse(boolean success, MessageApiResponse message) {
        this.success = success;
        this.message = message;
    }

    private RestApiResponse(boolean success) {
        this.success = success;
    }

    private RestApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static<T> RestApiResponse<Object> buildSuccess(T data) {
        return new RestApiResponse<Object>(Boolean.TRUE, data);
    }

    public static RestApiResponse<Object> buildFail(MessageApiResponse message) {
        return new RestApiResponse<Object>(Boolean.FALSE, message);
    }

    public static RestApiResponse<Object> buildFail(String code, String text) {
        MessageApiResponse message = new MessageApiResponse(code, text);
        return new RestApiResponse<Object>(Boolean.FALSE, message);

    }
    public static RestApiResponse<Object> buildFail(String code, String text,Object object) {
        MessageApiResponse message = new MessageApiResponse(code, text);
        return new RestApiResponse<Object>(Boolean.FALSE, message, object);

    }

    public static RestApiResponse<Object> buildSuccess() {
        return new RestApiResponse<Object>(Boolean.TRUE);
    }

    public static RestApiResponse<Object> buildFail() {
        return new RestApiResponse<Object>(Boolean.FALSE);
    }


}
