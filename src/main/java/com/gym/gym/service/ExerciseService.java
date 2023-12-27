package com.gym.gym.service;

import com.gym.gym.dto.ExerciseDTO;
import com.gym.gym.dto.ExerciseDTO;
import com.gym.gym.dto.Requests.CreateExerciseRequest;
import com.gym.gym.dto.Requests.CreateExerciseRequest;
import com.gym.gym.dto.Requests.UpdateExerciseRequest;
import com.gym.gym.dto.Responses.BaseResponse;
import com.gym.gym.dto.Responses.GetAllExercisesResponse;
import com.gym.gym.dto.Responses.GetExerciseResponse;
import com.gym.gym.entity.Exercise;
import com.gym.gym.entity.Exercise;
import com.gym.gym.mapper.ExerciseMapper;
import com.gym.gym.mapper.ExerciseMapper;
import com.gym.gym.repository.ExerciseRepository;
import com.gym.gym.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public BaseResponse createExercise(CreateExerciseRequest request){
        BaseResponse response = new BaseResponse();
        if(isExistingExercisesName(request.getName())) {
            response.setErrorMessage("This exercise name is existing.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }
        if(request.getComplexity() < 0 || request.getComplexity() > 10){
            response.setErrorMessage("Invalid complexity.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }
        exerciseRepository.save(exerciseMapper.toEntity(request));
        return response;
    }

    private boolean isExistingExercisesName (String name){
        return exerciseRepository.existsByName(name);
    }

    public GetAllExercisesResponse getAllExercise(){
        List<Exercise> exercisesEntity = exerciseRepository.findAll();
        List<ExerciseDTO> exercisesDTOS = exercisesEntity.stream().map(exerciseMapper::toExerciseDTO)
                .collect(Collectors.toList());
        GetAllExercisesResponse response = new GetAllExercisesResponse();
        response.setExercises(exercisesDTOS);
        return response;
    }

    public GetExerciseResponse getExerciseById(Long id) {//id, validirame,dali id e po-golqmo ot 0
        GetExerciseResponse response = new GetExerciseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Exercise> exercise = exerciseRepository.findById(id);//razreshava stoinosta da e 0
        if (exercise.isEmpty()) {
            response.setErrorMessage("Missing Exercise with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        response.setExercise(Optional.of(exerciseMapper.toExerciseDTO(exercise.get())));//2 casta
        return response;
    }

    public BaseResponse updateExercise(Long id, UpdateExerciseRequest request) {
        BaseResponse response = new BaseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        if(request.getComplexity() < 0 || request.getComplexity() > 10){
            response.setErrorMessage("Invalid complexity.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isEmpty()) {
            response.setErrorMessage("Missing exercise with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        if(!(request.getName().equals(exercise.get().getName())) && isExistingExercisesName(request.getName())){
            response.setErrorMessage("This email is existing.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }

        Exercise exerciseToEdit = exercise.get();
        exerciseToEdit.setName(request.getName());
        exerciseToEdit.setDescription(request.getDescription());
        exerciseToEdit.setMusclePart(request.getMusclePart());
        exerciseToEdit.setComplexity(request.getComplexity());

        exerciseRepository.save(exerciseToEdit);

        return response;
    }

    public BaseResponse deleteExercise(Long id) {
        BaseResponse response = new BaseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isEmpty()) {
            response.setErrorMessage("Missing Exercise with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        exerciseRepository.delete(exercise.get());
        return response;
    }
}
