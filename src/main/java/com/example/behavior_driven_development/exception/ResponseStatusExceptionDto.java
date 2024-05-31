package com.example.behavior_driven_development.exception;

public record ResponseStatusExceptionDto(
        String error,
        String path,
        String message,
        int status,
        String timestamp
) {
}
