package com.example.dietService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class NutritionSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;

    private String inputQuery;

    private Integer quantityG;

    private Integer calorie;
    private Integer protein;
    private Integer carb;
    private Integer fat;
    private Integer fiber;
    private Integer sugar;

    @CreatedDate
    private LocalDateTime createdAt;



}
