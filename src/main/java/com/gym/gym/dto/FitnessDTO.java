package com.gym.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FitnessDTO {
    private long id;
    private String type;
    private String name;
    private String location;
}
