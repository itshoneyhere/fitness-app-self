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

    public int calories;
    public int proteinG;
    public int carbsG;
    public int fatG;
    public int fiberG;
    public int sugarG;
}
