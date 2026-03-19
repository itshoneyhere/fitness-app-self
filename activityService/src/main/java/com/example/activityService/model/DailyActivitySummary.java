package com.example.activityService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint( columnNames = {"userId","activityDate"})
)
public class DailyActivitySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String userId;

    public Integer totalCalorie;

    public Integer totalSteps;

    public Integer totalActivityMinute;

    private LocalDate activityDate;

}
