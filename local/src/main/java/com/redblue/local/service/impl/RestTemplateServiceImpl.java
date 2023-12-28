package com.redblue.local.service.impl;


import com.redblue.local.dto.SmsRequestDTO;
import com.redblue.local.dto.SmsResponseDTO;
import com.redblue.local.service.RestTemplateService;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public void sendSms() {
        URI rest_uri = UriComponentsBuilder
                .fromUriString("https://test-redblue.kro.kr")
                .path("/api/sendSMS")
                .encode()
                .build()
                .toUri();

        SmsRequestDTO requestDTO = new SmsRequestDTO();
        requestDTO.setTitle("SMS Title 샘플");
        requestDTO.setContent("안녕하세요! SMS 샘플 테스트입니다.");
        requestDTO.setTargetPhoneNumber("+82-10-1234-1234");

        RequestEntity<SmsRequestDTO> requestEntity = RequestEntity
                .post(rest_uri)
                .header("Authorization", "kCY2FhAsOaJ8yfd7yDplQ7r7KYGQuE0pFJetSMQHv9ssHIKBfeA0mRtYzhXfAHdt")
                .body(requestDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SmsResponseDTO> responseEntity = restTemplate.exchange(requestEntity,
                SmsResponseDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

    }
}
