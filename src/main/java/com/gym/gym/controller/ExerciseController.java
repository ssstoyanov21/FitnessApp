package com.gym.gym.controller;

import com.gym.gym.dto.Requests.CreateExerciseRequest;
import com.gym.gym.dto.Requests.CreateExerciseRequest;
import com.gym.gym.dto.Requests.UpdateExerciseRequest;
import com.gym.gym.dto.Responses.BaseResponse;
import com.gym.gym.dto.Responses.GetAllExercisesResponse;
import com.gym.gym.dto.Responses.GetExerciseResponse;
import com.gym.gym.service.ExerciseService;
import com.gym.gym.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createExercise(@RequestBody CreateExerciseRequest request){
        BaseResponse response = exerciseService.createExercise(request);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public GetAllExercisesResponse getAllExercises() {
        return exerciseService.getAllExercise();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetExerciseResponse> getExerciseById(@PathVariable Long id) {
        GetExerciseResponse response = exerciseService.getExerciseById(id);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.ok(response);//vurni mi statuscode 200 s danite
    }

    @PutMapping("/update/{id}")//obrabotka nevalidni danni
    public ResponseEntity<BaseResponse> updateExercise(@PathVariable Long id, @RequestBody UpdateExerciseRequest request) {
        BaseResponse response = exerciseService.updateExercise(id, request);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteExercise(@PathVariable Long id) {
        BaseResponse response = exerciseService.deleteExercise(id);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.noContent().build();
    }
    


}
