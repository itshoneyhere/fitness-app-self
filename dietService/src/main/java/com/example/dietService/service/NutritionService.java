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

        LocalDate now = LocalDate.now();
        LocalDateTime todayStart  = now.atStartOfDay();
        LocalDateTime todayEnd  = todayStart.plusDays(1);

        List<UserDiet> userDiets = userDietRepo.findByUserIdAndCreatedAtBetween(userId,todayStart,todayEnd);

        return sumMacros(userDiets);
    }

    public UserDiet getLatestMacro(String userId)
    {
        return userDietRepo.findFirstByUserIdOrderByCreatedAtDesc(userId);
    }

    public UserDiet searchAndAddFood(String userId, String foodName, String quantity) {
        //search for food in db
        NutritionSummary nutritionSummary =  nutritionRepo.findFirstByFoodNameIgnoreCase(foodName) ;

        if(nutritionSummary != null)
        {
            return userDietRepo.save(Mapper.toUserDiet(nutritionSummary,userId));
        }
        //if not available ask ai
        NutritionSummary nutritionSummaryFromAi = Mapper.toNutritionSummaryEntity(aiService.getNutritionalDataFromAi(foodName,quantity).orElseThrow(() -> new RuntimeException("Exception: Failed to fetch nutrition from ai")));

        //save nutrition summary
        //add food to user diet
        return userDietRepo.save(Mapper.toUserDiet(nutritionRepo.save(nutritionSummaryFromAi),userId));

    }

    public MacrosOfDayDto sumMacros(List<UserDiet> diets) {
        int calories = diets.stream().mapToInt(UserDiet::getCalories).sum();
        int proteinG = diets.stream().mapToInt(UserDiet::getProteinG).sum();
        int carbsG   = diets.stream().mapToInt(UserDiet::getCarbsG).sum();
        int fatG     = diets.stream().mapToInt(UserDiet::getFatG).sum();
        int fiberG   = diets.stream().mapToInt(UserDiet::getFiberG).sum();
        int sugarG   = diets.stream().mapToInt(UserDiet::getSugarG).sum();

        return new MacrosOfDayDto(calories, proteinG, carbsG, fatG, fiberG, sugarG);
    }
}
