package com.gruppo3.gestioneComunity.dto.response;

import lombok.Builder;

@Builder
public record LikeResponse (
        Long id,
        Long NewsId,
        Long DipendenteId
){
}
