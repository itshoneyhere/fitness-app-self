package com.example.dietService.service;

import com.example.dietService.dto.MacrosOfDayDto;
import com.example.dietService.mapper.Mapper;
import com.example.dietService.model.NutritionSummary;
import com.example.dietService.model.UserDiet;
import com.example.dietService.repo.NutritionSummaryRepo;
import com.example.dietService.repo.UserDietRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private final UserDietRepo userDietRepo;
    private final NutritionSummaryRepo nutritionRepo;
    private final AiService aiService;

    public MacrosOfDayDto getMacrosToday(String userId) {

        LocalDate utcDate = LocalDate.now(ZoneOffset.UTC);
        LocalDateTime todayStart = utcDate.atStartOfDay(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime todayEnd= todayStart.plusDays(1);

        return sumMacros(userId,todayStart,todayEnd);
    }

    public MacrosOfDayDto searchAndAddFood(String userId, String foodName, Integer quantity) {
        //search for food in db

        NutritionSummary nutritionSummary =  nutritionRepo.findFirstByFoodNameIgnoreCase(foodName) ;

        if(nutritionSummary != null)
        {
            userDietRepo.save(Mapper.toUserDiet(nutritionSummary,userId));
            return getMacrosToday(userId);
        }

        //if not available ask ai
         //add to db
        NutritionSummary nutritionSummaryFromAi = Mapper.toNutritionSummaryEntity(aiService.getNutritionalDataFromAi(foodName,quantity).orElseThrow(() -> new RuntimeException("Exception: Failed to fetch nutrition from ai")));

        //add food to user diet
        userDietRepo.save(Mapper.toUserDiet(nutritionSummaryFromAi,userId));

        //save macros in nutrition summary
        nutritionRepo.save(nutritionSummaryFromAi);

        return getMacrosToday(userId);

    }

    public MacrosOfDayDto sumMacros(String userId,LocalDateTime start,LocalDateTime end) {

        return userDietRepo.calculateDailyTotal(userId,start,end);

    }
}
