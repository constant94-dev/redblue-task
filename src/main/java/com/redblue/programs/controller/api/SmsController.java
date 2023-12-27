package com.redblue.programs.controller.api;

import static com.redblue.programs.exception.ErrorCode.SMS_REQUEST_NOT_FOUND;

import com.redblue.programs.dto.SmsRequestDTO;
import com.redblue.programs.dto.SmsResponseDTO;
import com.redblue.programs.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SmsController {
    private final Logger LOGGER = LoggerFactory.getLogger(SmsController.class);

    @PostMapping(value = "/sendSMS")
    public ResponseEntity<SmsResponseDTO> send(
            @RequestBody SmsRequestDTO requestDTO,
            @RequestHeader(name = "Content-Type") String contentType
    ) {
        LOGGER.info("/api/sendSMS 호출!!\tContent-Type: " + contentType);
        SmsResponseDTO responseDTO = new SmsResponseDTO();

        if (requestDTO.getTitle() == null
                || requestDTO.getContent() == null
                || requestDTO.getTargetPhoneNumber() == null) {
            throw new CustomException(SMS_REQUEST_NOT_FOUND);
        }

        responseDTO.setMessage("Successfully sent");

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
