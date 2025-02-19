package com.gruppo3.user_service.dto.response;

import lombok.Builder;

@Builder
public record GenericResponse(
        String message
) {
}
