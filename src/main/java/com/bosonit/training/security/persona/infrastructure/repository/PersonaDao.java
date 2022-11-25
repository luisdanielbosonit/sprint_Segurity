package com.bosonit.training.security.persona.infrastructure.repository;

import com.bosonit.training.security.persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonaDao extends JpaRepository<Persona, Integer> {
   Persona findByUsername(String username);

}
