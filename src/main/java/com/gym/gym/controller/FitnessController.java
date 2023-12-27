package com.gym.gym.controller;

import com.gym.gym.dto.Requests.CreateFitnessRequest;
import com.gym.gym.dto.Requests.UpdateClientRequest;
import com.gym.gym.dto.Requests.UpdateFitnessRequest;
import com.gym.gym.dto.Responses.*;
import com.gym.gym.service.FitnessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fitness")
public class FitnessController {
    private final FitnessService fitnessService;
    public  FitnessController(FitnessService fitnessService) {
        this.fitnessService = fitnessService;
    }
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createFitness(@RequestBody CreateFitnessRequest request){
        BaseResponse response = fitnessService.createFitness(request);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public GetAllFitnessResponse getAllFitness() {
        return fitnessService.getAllFitness();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetFitnessResponse> getFitnessById(@PathVariable Long id) {
        GetFitnessResponse response = fitnessService.getFitnessById(id);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.ok(response);//vurni mi statuscode 200 s danite
    }

    @PutMapping("/update/{id}")//obrabotka nevalidni danni
    public ResponseEntity<BaseResponse> updateFitness(@PathVariable Long id, @RequestBody UpdateFitnessRequest request) {
        BaseResponse response = fitnessService.updateFitness(id, request);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteFitness(@PathVariable Long id) {
        BaseResponse response = fitnessService.deleteFitness(id);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.noContent().build();
    }


}
