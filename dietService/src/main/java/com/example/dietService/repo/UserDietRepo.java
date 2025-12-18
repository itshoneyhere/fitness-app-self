package com.example.dietService.repo;

import com.example.dietService.model.UserDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserDietRepo extends JpaRepository<UserDiet,Long> {

    List<UserDiet> findByUserIdAndCreatedAtBetween(String userId, LocalDateTime start, LocalDateTime end);

    UserDiet findFirstByUserIdOrderByCreatedAtDesc(String userId);
}
