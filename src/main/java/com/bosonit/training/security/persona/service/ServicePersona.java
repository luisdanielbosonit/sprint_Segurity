package com.bosonit.training.security.persona.service;

import com.bosonit.training.security.persona.entity.Persona;
import com.bosonit.training.security.role.entity.Role;


import java.util.List;

public interface ServicePersona {


    public List<Persona> viewall();

    public Persona savePersona(Persona persona)throws Exception; //ok

    public Role saveRole(Role role);

    public void addRoleToUser(String username, String nameRole);

    Persona getPersona(String username);
}
