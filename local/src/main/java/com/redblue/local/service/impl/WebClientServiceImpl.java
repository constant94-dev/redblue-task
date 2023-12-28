package com.redblue.local.service.impl;

import com.redblue.local.dto.SmsRequestDTO;
import com.redblue.local.dto.SmsResponseDTO;
import com.redblue.local.service.WebClientService;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class WebClientServiceImpl implements WebClientService {
    private final Logger LOGGER = LoggerFactory.getLogger(WebClientServiceImpl.class);

    @Override
    public void sendSms() {
        URI web_uri = UriComponentsBuilder
                .fromUriString("https://test-redblue.kro.kr")
                .path("/api/sendSMS")
                .encode()
                .build()
                .toUri();

        SmsRequestDTO requestDTO = new SmsRequestDTO();
        requestDTO.setTitle("SMS Title 샘플");
        requestDTO.setContent("안녕하세요! SMS 샘플 테스트입니다.");
        requestDTO.setTargetPhoneNumber("+82-10-1234-1234");

        WebClient webClient = WebClient.create();

        Mono<ResponseEntity<SmsResponseDTO>> responseEntity = webClient.post()
                .uri(web_uri)
                .header("Authorization", "kCY2FhAsOaJ8yfd7yDplQ7r7KYGQuE0pFJetSMQHv9ssHIKBfeA0mRtYzhXfAHdt")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestDTO), SmsRequestDTO.class)
                .retrieve()
                .toEntity(SmsResponseDTO.class);

        responseEntity.subscribe(res -> {
            HttpStatusCode status = res.getStatusCode();
            LOGGER.info("status code : {}", status);

            HttpHeaders headers = res.getHeaders();
            LOGGER.info("headers : {}", headers);

            SmsResponseDTO responseDTO = res.getBody();
            LOGGER.info("body : {}", responseDTO);
        });
    }
}
