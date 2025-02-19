package com.gruppo3.user_service.dto.request;

public record LoginRequest(
        String email,
        String password
) {}