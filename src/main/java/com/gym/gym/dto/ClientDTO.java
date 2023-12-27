package com.gym.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private double kg;
}
