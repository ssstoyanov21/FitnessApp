package com.gym.gym.dto.Responses;

import com.gym.gym.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends BaseResponse{
    private Optional<Role> role;
}
