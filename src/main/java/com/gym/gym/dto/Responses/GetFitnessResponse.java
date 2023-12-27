package com.gym.gym.dto.Responses;

import com.gym.gym.dto.FitnessDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFitnessResponse extends BaseResponse {

    private Optional<FitnessDTO> fitness;
}
