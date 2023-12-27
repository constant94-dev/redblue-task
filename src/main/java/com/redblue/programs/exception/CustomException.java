package com.redblue.programs.exception;

/* 전역으로 사용할 클래스
* RuntimeException 상속해 Unchecked Exception 활용
* 생성자로 ErrorCode 받기 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
}
