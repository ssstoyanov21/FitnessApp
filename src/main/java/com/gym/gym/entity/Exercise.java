package com.gym.gym.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table(name = "Exercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column
    private String musclePart;
    @Column
    private String fitnessType;
    @Column(nullable = false)
    private Integer complexity;


}
