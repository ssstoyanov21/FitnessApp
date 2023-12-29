package com.gym.gym.dto.Responses;

import com.gym.gym.dto.ExerciseDTO;
import com.gym.gym.dto.FitnessDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFitnessDetailsResponse extends BaseResponse {
    private Optional<FitnessDTO> fitness;
    private List<ExerciseDTO> exercises=new ArrayList<ExerciseDTO>();
}
