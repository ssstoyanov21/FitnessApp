package com.gym.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;
    private String musclePart;
    private String fitnessType;
    private Integer complexity;
}
