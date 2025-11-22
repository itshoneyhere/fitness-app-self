package com.example.userServer.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI swaggerConfigs()
    {
        return new OpenAPI().info(
                new Info().title("User Service Apis")
                        .description("By Honey")
                        .version("1.0")
        );

    }
}
