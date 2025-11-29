package com.example.activityService.repo;

import com.example.activityService.model.ActivityRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends JpaRepository<ActivityRecords,Long> {


    List< ActivityRecords> findAllByUserId(String userId);

    ActivityRecords findTopByUserIdOrderByActivityIdDesc(String userId);
}
