package com.example.dietService.dto;
import java.util.Map;

public record ActivityResponseDto(

        String userId,

        String activity ,

        Integer moveMinutes,

        Integer calorieBurned,

        Map<String,Object> activityDetails
) {
}