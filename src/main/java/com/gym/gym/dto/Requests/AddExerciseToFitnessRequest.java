package com.gym.gym.dto.Requests;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddExerciseToFitnessRequest {
    @NotBlank(message = "IdFitness is mandatory!")
    private Long fitnessId;
    @NotBlank(message = "IdExercise is mandatory!")
    private  Long exerciseId;


}
