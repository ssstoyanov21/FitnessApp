package com.gym.gym.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "FitnessToExercise")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FitnessToExercise {
    //obedenie za mnogo fitness kam mnogo uprajneniq
    //next lvl-lejanka
    //next lvl 2 - losotve
    //next lvl-deadlift
    //id, fitnes, ime na upj;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    //@MapsId("id")
    @JoinColumn(name = "fitness_id")
    private Fitness fitness;

    @ManyToOne
    //@MapsId("id")
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;


}
