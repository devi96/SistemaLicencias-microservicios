package com.gestionlicencias.cloud_gateway.exception;

import java.time.LocalDateTime;

public record CustomErrorResponse(
        LocalDateTime datetime,
        String message,
        String messageDetail
) {
}
