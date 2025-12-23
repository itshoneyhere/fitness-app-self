package com.example.dietService.service;

import com.example.dietService.dto.ActivityResponseDto;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ActivityClient {

    private final WebClient webClient;

    public ActivityClient(WebClient.Builder builder)
    {
        this.webClient = builder.baseUrl("http://localhost:8087").build();
    }

    public ActivityResponseDto getLatestActivity(String jwt)
    {
        return webClient.get()
                .uri("/api/activities/users/last")
                .header(HttpHeaders.AUTHORIZATION,"Bearer " +jwt)
                .retrieve()
                .bodyToMono(ActivityResponseDto.class)
                .block();
    }



}
