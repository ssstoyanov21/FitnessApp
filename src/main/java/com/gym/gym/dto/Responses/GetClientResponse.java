package com.gym.gym.dto.Responses;

import com.gym.gym.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetClientResponse extends BaseResponse{
    private Optional<ClientDTO> client;//clienta moge da e prazen
}
