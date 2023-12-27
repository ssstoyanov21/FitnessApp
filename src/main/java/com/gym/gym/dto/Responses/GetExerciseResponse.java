package com.gym.gym.dto.Responses;

import com.gym.gym.dto.ExerciseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExerciseResponse extends BaseResponse {
    private Optional<ExerciseDTO> exercise;
}
