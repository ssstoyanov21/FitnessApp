package com.gym.gym.dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientResponse extends BaseResponse{
    private Optional<Long> id;
}
