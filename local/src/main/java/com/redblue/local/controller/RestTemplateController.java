package com.redblue.local.controller;

import com.redblue.local.dto.SmsRequestDTO;
import com.redblue.local.service.RestTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {
    private RestTemplateService restTemplateService;

    @Autowired
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @Operation(
            summary = "title, content, targetPhoneNumber",
            tags = {"POST"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(schema = @Schema(implementation = SmsRequestDTO.class), mediaType = "application/json")
                    }),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN",
                    content = {
                            @Content(schema = @Schema())
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = {
                            @Content(schema = @Schema())
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SEVER ERROR",
                    content = {
                            @Content(schema = @Schema())
                    })
    })
    @PostMapping(value = "/sms")
    public void send() {
        restTemplateService.sendSms();
    }
}
