package com.redblue.remote.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 403 Forbidden: 콘텐츠 접근할 권리 없음 */
    // TODO: 401과 다른 점은 서버는 클라이언트가 누구인지 알고 있다
    INVALID_BEARER_TOKEN(FORBIDDEN, "권한 정보가 없는 토큰입니다."),

    /* 404 Not Found: 요청 리소스 찾을 수 없음 */
    SMS_REQUEST_NOT_FOUND(NOT_FOUND, "요청 데이터를 찾을 수 없습니다."),

    /* 500 Internal Server Error: 서버 처리 문제 */
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 처리에 문제가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
