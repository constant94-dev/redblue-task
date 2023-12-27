package com.redblue.programs.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/* 클라이언트에게 보낼 응답 Format
* 파라미터로 ErrorCode를 받아 ResponseEntity<ErrorResponse> 변환
* */

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build());
    }
}
