package com.gym.gym.mapper;

import com.gym.gym.dto.ClientDTO;
import com.gym.gym.dto.Requests.CreateClientRequest;
import com.gym.gym.entity.Client;
import com.gym.gym.enums.Role;
import org.springframework.stereotype.Component; //check-ni

import java.time.LocalDateTime;

@Component
public class ClientMapper {
    public ClientDTO toClientDTO(Client client) { //definition
        return ClientDTO.builder().id(client.getId())
                .email(client.getEmail())
                .password(client.getPassword())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .kg(client.getKg())
                .role(client.getRole())
                .build();
    }

    public Client toEntity(CreateClientRequest dto) {
        return Client.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .kg(dto.getKg())
                .role(Role.ROLE_CLIENT)
                .createAt(LocalDateTime.now())
                .build();
    }
}
