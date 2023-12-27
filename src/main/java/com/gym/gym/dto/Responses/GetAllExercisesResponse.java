package com.gym.gym.dto.Responses;

import com.gym.gym.dto.ExerciseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllExercisesResponse extends BaseResponse {
    private List<ExerciseDTO> exercises;
}
