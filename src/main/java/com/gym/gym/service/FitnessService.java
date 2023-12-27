package com.gym.gym.service;

import com.gym.gym.dto.ClientDTO;
import com.gym.gym.dto.FitnessDTO;
import com.gym.gym.dto.Requests.CreateFitnessRequest;
import com.gym.gym.dto.Requests.UpdateClientRequest;
import com.gym.gym.dto.Requests.UpdateFitnessRequest;
import com.gym.gym.dto.Responses.*;
import com.gym.gym.entity.Client;
import com.gym.gym.entity.Fitness;
import com.gym.gym.mapper.ClientMapper;
import com.gym.gym.mapper.FitnessMapper;
import com.gym.gym.repository.ClientRepository;
import com.gym.gym.repository.FitnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FitnessService {
    private final FitnessRepository fitnessRepository;
    private final FitnessMapper fitnessMapper;
    @Autowired
    public FitnessService(FitnessRepository fitnessRepository, FitnessMapper fitnessMapper) {
        this.fitnessRepository = fitnessRepository;
        this.fitnessMapper = fitnessMapper;
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

}
