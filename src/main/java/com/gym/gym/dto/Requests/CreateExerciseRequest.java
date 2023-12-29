package com.gym.gym.dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateExerciseRequest {
    @NotBlank(message = "Name is mandatory!")
    private String name;
    private String description;
    @NotBlank(message = "Muscle part is mandatory!")
    private String musclePart;
    @NotBlank(message = "Fitness Type is mandatory!")
    private String fitnessType;
    @NotBlank(message = "Complexity is mandatory!")
    private Integer complexity;
}
