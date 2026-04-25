package com.insurance.policy.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;
    private long timestamp;

    public ErrorResponse(String message, int status, LocalDateTime now){
        this.message = message;
        this.status = status;
        this.timestamp = System.currentTimeMillis();

    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
