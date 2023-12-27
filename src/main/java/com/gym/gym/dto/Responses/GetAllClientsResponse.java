package com.gym.gym.dto.Responses;

import com.gym.gym.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllClientsResponse extends BaseResponse{//nasledqvane
    private List<ClientDTO> clients;
}
