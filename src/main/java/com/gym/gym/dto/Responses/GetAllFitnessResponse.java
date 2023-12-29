package com.gym.gym.dto.Responses;

import com.gym.gym.dto.FitnessDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFitnessResponse extends BaseResponse {
    private List<FitnessDTO> fitness;
}
