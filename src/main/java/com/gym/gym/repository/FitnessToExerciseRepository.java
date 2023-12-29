package com.gym.gym.repository;

import com.gym.gym.entity.FitnessToExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessToExerciseRepository extends JpaRepository<FitnessToExercise, Long> {

}
