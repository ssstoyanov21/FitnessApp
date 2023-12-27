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
public class UpdateClientRequest {
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid email address!")
    private String email;
    @NotBlank(message = "Password is mandatory!")
    private  String password;
    //email e zadaljitelno pole i dali emaila e validen , parolata e zadaljitelna
    @NotBlank(message = "First name is mandatory!")
    private String firstName;
    @NotBlank(message = "Last name is mandatory!")
    private  String lastName;
    private  Integer age;
    private  double kg;
}
