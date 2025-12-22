package com.example.dietService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MacrosOfDayDto {

    public Long calorie;
    public Long protein;
    public Long carb;
    public Long fat;
    public Long fiber;
    public Long sugar;
}
