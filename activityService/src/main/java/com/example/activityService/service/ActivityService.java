package com.example.activityService.service;

import com.example.activityService.dto.RequestDto;
import com.example.activityService.mapper.Mapper;
import com.example.activityService.model.ActivityRecords;
import com.example.activityService.repo.ActivityRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {
    private final ActivityRepo activityRepo;


    public List<ActivityRecords> getActivityByUserId(String userId) {
        return  activityRepo.findAllByUserId(userId);
    }

    public ActivityRecords createActivity(String userId, RequestDto requestDto) {
        ActivityRecords activityRecords = Mapper.fromRequest(requestDto);
        activityRecords.setUserId(userId);
        return activityRepo.save(activityRecords);
    }

    public ActivityRecords getLastActivityOfUser(String userId) {
        return activityRepo.findTopByUserIdOrderByActivityIdDesc(userId);
    }
}
