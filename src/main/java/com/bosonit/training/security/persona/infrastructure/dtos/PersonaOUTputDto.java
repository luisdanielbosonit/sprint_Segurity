package com.bosonit.training.security.persona.infrastructure.dtos;

import com.bosonit.training.security.persona.entity.Persona;
import com.bosonit.training.security.role.entity.Role;
import lombok.Data;

import java.util.Collection;


/**
 * A DTO for the {@link Persona} entity
 */
@Data
public class PersonaOUTputDto {

    private Integer person_id;
    private String username;
    private String name;
    private String email;
    private Collection<Role> roles;


    public PersonaOUTputDto(Persona persona){
        this.person_id = persona.getIdPersona();
        this.username = persona.getUsername();
        this.name = persona.getName();

        this.roles=persona.getRoles();

    }
}