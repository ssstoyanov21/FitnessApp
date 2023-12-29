package com.gym.gym.dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid email address!")
    private String email;
    @NotBlank(message = "Password is mandatory!")
    private String password;
}
