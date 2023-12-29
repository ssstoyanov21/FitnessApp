package com.gym.gym.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Fitness")
@Builder
@Entity
public class Fitness {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String location;
    //da napravim otdelno entity fitnes kam client many ot many
    @OneToMany(mappedBy = "fitness", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  final List<FitnessToExercise> fitnessToExercises = new ArrayList<FitnessToExercise>();

}
