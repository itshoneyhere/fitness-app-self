package com.example.dietService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddFoodRequestDto {


    @NotBlank
    public String foodName;

    @NotBlank
    public String quantity;
}
