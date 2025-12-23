package com.example.dietService.repo;

import com.example.dietService.dto.MacrosOfDayDto;
import com.example.dietService.model.UserDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserDietRepo extends JpaRepository<UserDiet,Long> {

    List<UserDiet> findByUserIdAndCreatedAtBetween(String userId, LocalDateTime start, LocalDateTime end);

    @Query("Select new com.example.dietService.dto.MacrosOfDayDto(" +
            "Sum(u.calorie),Sum(u.carb),Sum(u.protein),Sum(u.fiber), "+
            "Sum(u.fat),Sum(u.sugar)) "+
            "From UserDiet u "+
            "Where u.userId= :userId AND u.createdAt >= :start AND u.createdAt < :end "
    )
    MacrosOfDayDto calculateDailyTotal(@Param("userId") String userId,
                                       @Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);

}
