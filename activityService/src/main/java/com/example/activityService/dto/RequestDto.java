package com.example.activityService.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record RequestDto(



        String activity ,

        Integer moveMinutes,

        Integer calorieBurned,

        Map<String,Object> activityDetails


        )
{

}
