package com.example.dietService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiResponseDto {

    public String food_name;
    public String input_query;

    public Macros macros;
    public Serving serving;

    public static class Macros {
        public Integer calories;
        public Integer protein_g;
        public Integer carbs_g;
        public Integer fat_g;
        public Integer fiber_g;
        public Integer sugar_g;
    }

    public static class Serving {
        public Integer value;
        public String unit;
    }

}
