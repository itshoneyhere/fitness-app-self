package com.example.activityService.mapper;

import com.example.activityService.dto.RequestDto;
import com.example.activityService.dto.ResponseDto;
import com.example.activityService.model.ActivityRecords;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static ActivityRecords fromRequest(RequestDto dto) {
        ActivityRecords record = new ActivityRecords();

        record.setActivity(dto.activity());
        record.setMoveMinutes(dto.moveMinutes());
        record.setCalorieBurned(dto.calorieBurned());
        record.setActivityDetails(dto.activityDetails());
        return record;
    }


    public static ResponseDto toResponse(ActivityRecords entity) {
        return new ResponseDto(
                entity.getUserId(),
                entity.getActivity(),
                entity.getMoveMinutes(),
                entity.getCalorieBurned(),
                entity.getActivityDetails()

        );
    }
}
