package com.example.activityService.controller;

import com.example.activityService.dto.RequestDto;
import com.example.activityService.dto.ResponseDto;
import com.example.activityService.mapper.Mapper;
import com.example.activityService.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<List< ResponseDto>> getActivityPerUser(@AuthenticationPrincipal Jwt jwt)
    {
        String userId = jwt.getSubject();

        return ResponseEntity.ok(activityService.getActivityByUserId(userId).stream().map(Mapper::toResponse).toList());
    }

    @GetMapping("/users/last")
    public ResponseEntity<ResponseDto> getLastActivityOfUser(@AuthenticationPrincipal Jwt jwt)
    {
        String userId = jwt.getSubject();
       return ResponseEntity.ok( Mapper.toResponse( activityService.getLastActivityOfUser(userId)));
    }



    @PostMapping
    public ResponseEntity<ResponseDto> createActivity(@RequestBody RequestDto requestDto,
                                                      @AuthenticationPrincipal Jwt jwt)
    {
        String userId = jwt.getSubject();

        return  ResponseEntity.ok( Mapper.toResponse( activityService.createActivity(userId,requestDto)));

    }
}
