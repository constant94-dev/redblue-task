package com.redblue.remote.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsRequestDTO {
    private String title;
    private String content;
    private String targetPhoneNumber;
}
