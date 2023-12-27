package com.gym.gym.mapper;

import com.gym.gym.dto.ExerciseDTO;
import com.gym.gym.dto.Requests.CreateExerciseRequest;
import com.gym.gym.entity.Exercise;
import org.springframework.stereotype.Component;
@Component
public class ExerciseMapper {
    public ExerciseDTO toExerciseDTO(Exercise exercise){
        return ExerciseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .musclePart(exercise.getMusclePart())
                .fitnessType(exercise.getFitnessType())
                .complexity(exercise.getComplexity())
                .build();
    }
    public Exercise toEntity(ExerciseDTO dto){
        return Exercise.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .musclePart(dto.getMusclePart())
                .fitnessType(dto.getFitnessType())
                .complexity(dto.getComplexity())
                .build();
    }

    public Exercise toEntity(CreateExerciseRequest request){
        return Exercise.builder()
                .name(request.getName())
                .description(request.getDescription())
                .musclePart(request.getMusclePart())
                .fitnessType(request.getFitnessType())
                .complexity(request.getComplexity())
                .build();
    }



}
