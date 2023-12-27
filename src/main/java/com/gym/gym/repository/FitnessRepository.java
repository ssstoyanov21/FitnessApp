package com.gym.gym.repository;

import com.gym.gym.entity.Fitness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessRepository extends JpaRepository<Fitness, Long> {
    boolean existsByName(String name);
}
