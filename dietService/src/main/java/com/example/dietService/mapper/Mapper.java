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
                .calorie(m != null ? m.calories : null)
                .protein(m != null ? m.protein_g : null)
                .carb(m != null ? m.carbs_g : null)
                .fat(m != null ? m.fat_g : null)
                .fiber(m != null ? m.fiber_g : null)
                .sugar(m != null ? m.sugar_g : null)

                .build();
    }

    public static UserDiet toUserDiet(NutritionSummary ns, String userId) {

        if (ns == null) {
            return null;
        }

        return UserDiet.builder()
                .userId(userId)                     // provided externally
                .foodId(ns.getId())
                .quantityG(ns.getQuantityG())// provided externally
                .calorie(ns.getCalorie())
                .protein(ns.getProtein())
                .carb(ns.getCarb())
                .fat(ns.getFat())
                .fiber(ns.getFiber())
                .sugar(ns.getSugar())
                .build();
    }



}
