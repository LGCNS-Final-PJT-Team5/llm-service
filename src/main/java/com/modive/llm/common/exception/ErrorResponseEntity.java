package com.modive.llm.common.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponseEntity {
    private int statusCode;
    private String message;
    private String error;

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e){
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponseEntity.builder()
                        .statusCode(e.getHttpStatus().value())
                        .message(e.getMessage())
                        .error(e.getHttpStatus().getReasonPhrase())
                        .build());
    }
}
