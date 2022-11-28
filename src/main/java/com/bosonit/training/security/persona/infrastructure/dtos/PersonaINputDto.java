package com.bosonit.training.security.persona.infrastructure.dtos;

import com.bosonit.training.security.persona.entity.Persona;
import com.bosonit.training.security.role.entity.Role;
import lombok.Data;

import java.util.Collection;


/**
 * A DTO for the {@link Persona} entity
 */
@Data
public class PersonaINputDto {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Collection<Role> roles;

    public PersonaINputDto(Integer id, String username, String password, String name, String personalEmail, Collection<Role> roles) {
            setId(id);
            setUsername(username);
            setPassword(password);
            setName(name);
            setEmail(personalEmail);
            setRoles(roles);
    }
    public Persona transformIntoPersona() {
        Persona person = new Persona();
        person.setIdPersona(this.id);
        person.setPassword(this.password);
        person.setUsername(this.username);
        person.setName(this.name);
        person.setRoles(this.roles);



        return person;
    }


}