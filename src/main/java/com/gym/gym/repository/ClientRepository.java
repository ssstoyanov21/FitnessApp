package com.gym.gym.repository;

import com.gym.gym.entity.Client;
import com.gym.gym.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
    Optional<Client> findByEmail(String email);
    List<Client> findByRoleIsNot(Role role);
}
