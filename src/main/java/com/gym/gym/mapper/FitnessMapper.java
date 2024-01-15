package com.gym.gym.mapper;

import com.gym.gym.dto.FitnessDTO;
import com.gym.gym.dto.Requests.CreateFitnessRequest;
import com.gym.gym.entity.Fitness;
import org.springframework.stereotype.Component;

@Component
public class FitnessMapper {
    public FitnessDTO toFitnessDTO(Fitness fitness){
        return FitnessDTO.builder()
                .id(fitness.getId())
                .type(fitness.getType())
                .name(fitness.getName())
                .location(fitness.getLocation())
                .build();
    }

    public Fitness toEntity(CreateFitnessRequest request){
        return Fitness.builder()
                .type(request.getType())
                .name(request.getName())
                .location(request.getLocation())
                .build();
    }
}
