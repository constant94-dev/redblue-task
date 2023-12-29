package com.redblue.local.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 스프링 기반 애플리케이션에 대한 JSON API 문서 */

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());

    }

    private Info apiInfo() {
        return new Info()
                .title("RedBlue Task for Swagger Doc")
                .description("RestTemplate vs WebClient 통신")
                .version("1.0.0");
    }
}
