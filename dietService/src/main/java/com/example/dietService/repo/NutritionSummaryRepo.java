package com.example.dietService.repo;

import com.example.dietService.model.NutritionSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionSummaryRepo extends JpaRepository<NutritionSummary,Long> {
    NutritionSummary findFirstByFoodNameIgnoreCase(String foodName);
}
