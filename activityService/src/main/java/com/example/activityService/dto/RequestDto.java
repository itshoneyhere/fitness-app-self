package com.example.activityService.dto;

import com.example.activityService.model.ActivityType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public record RequestDto(

        @NotNull
        String activity,

        Integer minutes,

        Integer calorieBurned,

        Integer stepCount,

        LocalDateTime startTime,

        Map<String, Object> details

) {

}
