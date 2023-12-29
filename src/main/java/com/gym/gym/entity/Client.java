package com.gym.gym.entity;
import com.gym.gym.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;



@Table(name = "Client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    //ne dopuskame da e prazna stoinosta, ne mogem da zapazim 2 razlichni emaila
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @NotNull
    private Integer age;
    @Column
    private double kg;
    @Column
    private LocalDateTime createAt;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role role = Role.ROLE_CLIENT;

}
