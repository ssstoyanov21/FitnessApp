package com.gym.gym.dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFitnessRequest {

    @NotBlank(message = "Type is mandatory!")
    private String type;
    @NotBlank(message = "Name is mandatory!")
    private String name;
    @NotBlank(message = "Location is mandatory!")
    private String location;

}
