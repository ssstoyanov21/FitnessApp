package com.gym.gym.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  final List<FitnessToExercise> fitnessToExercises = new ArrayList<FitnessToExercise>();
    //suzdava vruska 1kam mnogo i ima mejdinna tablica exercise, lazy-to ne e zadaljitelno kato vrushta resulta
    //cascade - da ne ostavat neshta v tablicata;


}
