package com.example.dietService.controller;

import com.example.dietService.dto.AddFoodRequestDto;
import com.example.dietService.service.ActivityClient;
import com.example.dietService.service.NutritionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diet")
@RequiredArgsConstructor
public class DietController {

    private final NutritionService nutritionService;
    private final ActivityClient activityClient;

    @PostMapping("/users/add/food")
    public ResponseEntity<?> addUsersMeal(@RequestBody @Valid AddFoodRequestDto addFoodRequestDto,
                                          @AuthenticationPrincipal Jwt jwt)
    {
        String userId = jwt.getSubject();

        Integer quant  = Integer.parseInt( addFoodRequestDto.getQuantity());

        return ResponseEntity.ok( nutritionService.searchAndAddFood(userId,
                addFoodRequestDto.getFoodName().toLowerCase(),
                quant
                ));
    }


@GetMapping("/users/macros/today")
public ResponseEntity<?> getMacrosOfTheDay(@AuthenticationPrincipal Jwt jwt){
    String userId = jwt.getSubject();

    return ResponseEntity.ok( nutritionService.getMacrosToday(userId));
}

@GetMapping("/test/testSyncComm")
    public ResponseEntity<?> testSyncCommWebClient(@AuthenticationPrincipal Jwt jwt)
{
return ResponseEntity.ok( activityClient.getLatestActivity(jwt.getTokenValue()));
}

}
