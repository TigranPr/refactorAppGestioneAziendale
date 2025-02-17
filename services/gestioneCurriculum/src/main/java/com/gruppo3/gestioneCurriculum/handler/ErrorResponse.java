package com.gruppo3.gestioneCurriculum.handler;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {
    private String exception;
    private String message;
}
