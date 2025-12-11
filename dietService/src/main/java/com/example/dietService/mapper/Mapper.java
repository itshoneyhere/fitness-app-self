package com.example.dietService.mapper;

import com.example.dietService.dto.AiResponseDto;
import com.example.dietService.model.NutritionSummary;
import com.example.dietService.model.UserDiet;

public class Mapper {

    public static NutritionSummary toNutritionSummaryEntity(AiResponseDto dto) {

        if (dto == null) {
            return null;
        }

        AiResponseDto.Macros m = dto.getMacros();
        AiResponseDto.Serving s = dto.getServing();

        return NutritionSummary.builder()
                .foodName(dto.getFood_name())
                .inputQuery(dto.getInput_query())
                .quantityG(s != null ? s.value : null)

                .calories(m != null ? m.calories : null)
                .proteinG(m != null ? m.protein_g : null)
                .carbsG(m != null ? m.carbs_g : null)
                .fatG(m != null ? m.fat_g : null)
                .fiberG(m != null ? m.fiber_g : null)
                .sugarG(m != null ? m.sugar_g : null)

                .build();
    }

    public static UserDiet toUserDiet(NutritionSummary ns, String userId) {

        if (ns == null) {
            return null;
        }

        return UserDiet.builder()
                .userId(userId)                     // provided externally
                .foodId(ns.getId())                     // provided externally
                .calories(ns.getCalories())
                .proteinG(ns.getProteinG())
                .carbsG(ns.getCarbsG())
                .fatG(ns.getFatG())
                .fiberG(ns.getFiberG())
                .sugarG(ns.getSugarG())
                .build();
    }



}
