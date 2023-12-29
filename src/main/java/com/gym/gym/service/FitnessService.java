package com.gym.gym.service;

import com.gym.gym.dto.ExerciseDTO;
import com.gym.gym.dto.FitnessDTO;
import com.gym.gym.dto.Requests.AddExerciseToFitnessRequest;
import com.gym.gym.dto.Requests.CreateFitnessRequest;
import com.gym.gym.dto.Requests.UpdateFitnessRequest;
import com.gym.gym.dto.Responses.*;
import com.gym.gym.entity.Exercise;
import com.gym.gym.entity.Fitness;
import com.gym.gym.entity.FitnessToExercise;
import com.gym.gym.mapper.ExerciseMapper;
import com.gym.gym.mapper.FitnessMapper;
import com.gym.gym.repository.ExerciseRepository;
import com.gym.gym.repository.FitnessRepository;
import com.gym.gym.repository.FitnessToExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FitnessService {
    private final FitnessRepository fitnessRepository;
    private final FitnessMapper fitnessMapper;
    private final ExerciseRepository exerciseRepository;
    private final FitnessToExerciseRepository fitnessToExerciseRepository;
    private final ExerciseMapper exerciseMapper;

    @Autowired
    public FitnessService(FitnessRepository fitnessRepository, FitnessMapper fitnessMapper, ExerciseRepository exerciseRepository, FitnessToExerciseRepository fitnessToExerciseRepository, ExerciseMapper exerciseMapper) {
        this.fitnessRepository = fitnessRepository;
        this.fitnessMapper = fitnessMapper;
        this.exerciseRepository = exerciseRepository;
        this.fitnessToExerciseRepository = fitnessToExerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }
    public BaseResponse createFitness(CreateFitnessRequest request){
        BaseResponse response = new BaseResponse();
        if(isExistingFitnessName(request.getName())) {
            response.setErrorMessage("This fitness name is existing.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }
        fitnessRepository.save(fitnessMapper.toEntity(request));
        return response;
    }
    private boolean isExistingFitnessName (String name){
        return fitnessRepository.existsByName(name);
    }

    public GetAllFitnessResponse getAllFitness(){
        List<Fitness> fitnessEntity = fitnessRepository.findAll();
        List<FitnessDTO> fitnessDTOS = fitnessEntity.stream().map(fitnessMapper::toFitnessDTO)
                .collect(Collectors.toList());
        GetAllFitnessResponse response = new GetAllFitnessResponse();
        response.setFitness(fitnessDTOS);
        return response;
    }

    public GetFitnessResponse getFitnessById(Long id) {//id, validirame,dali id e po-golqmo ot 0
        GetFitnessResponse response = new GetFitnessResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Fitness> fitness = fitnessRepository.findById(id);//razreshava stoinosta da e 0
        if (fitness.isEmpty()) {
            response.setErrorMessage("Missing fitness with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        response.setFitness(Optional.of(fitnessMapper.toFitnessDTO(fitness.get())));//2 casta
        return response;
    }

    public BaseResponse updateFitness(Long id, UpdateFitnessRequest request) {
        BaseResponse response = new BaseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Fitness> fitness = fitnessRepository.findById(id);
        if (fitness.isEmpty()) {
            response.setErrorMessage("Missing fitness with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        if(!(request.getName().equals(fitness.get().getName())) && isExistingFitnessName(request.getName())){
            response.setErrorMessage("This email is existing.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }

        Fitness fitnessToEdit = fitness.get();
        fitnessToEdit.setName(request.getName());
        fitnessToEdit.setType(request.getType());
        fitnessToEdit.setLocation(request.getLocation());
        fitnessRepository.save(fitnessToEdit);

        return response;
    }

    public BaseResponse deleteFitness(Long id) {
        BaseResponse response = new BaseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Fitness> fitness = fitnessRepository.findById(id);
        if (fitness.isEmpty()) {
            response.setErrorMessage("Missing fitness with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        fitnessRepository.delete(fitness.get());
        return response;
    }
    public  BaseResponse addExerciseToFitness(AddExerciseToFitnessRequest request){
        BaseResponse response = new BaseResponse();
        if (request.getFitnessId() <= 0 || request.getExerciseId() <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Fitness> fitness = fitnessRepository.findById(request.getFitnessId());
        if (fitness.isEmpty()) {
            response.setErrorMessage("Missing fitness with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Exercise> exercise = exerciseRepository.findById(request.getExerciseId());//razreshava stoinosta da e 0
        if (exercise.isEmpty()) {
            response.setErrorMessage("Missing Exercise with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        FitnessToExercise fitnessToExercise = new FitnessToExercise();
        fitnessToExercise.setExercise(exercise.get());
        fitnessToExercise.setFitness(fitness.get());
        fitnessToExerciseRepository.save(fitnessToExercise);
        return  response;
    }
    public GetFitnessDetailsResponse getFitnessDetailsById(Long id) {//id, validirame,dali id e po-golqmo ot 0
        GetFitnessDetailsResponse response = new GetFitnessDetailsResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Fitness> fitness = fitnessRepository.findById(id);//razreshava stoinosta da e 0
        if (fitness.isEmpty()) {
            response.setErrorMessage("Missing fitness with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        response.setFitness(Optional.of(fitnessMapper.toFitnessDTO(fitness.get())));//2 casta
        List<FitnessToExercise> fitnessToExercises = fitness.get().getFitnessToExercises();//java generira avtomatichno left join-a
        List<Exercise> exercises = fitnessToExercises
                 .stream()
                 .map(fitnessToExercise -> fitnessToExercise.getExercise())
                 .toList();

        response.setExercises(exercises.stream()
                        .map(exerciseMapper::toExerciseDTO).toList());
        return response;
    }

}
