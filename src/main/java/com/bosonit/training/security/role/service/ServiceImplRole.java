package com.bosonit.training.security.role.service;

import com.bosonit.training.security.Exception.EntityNotFoundException;

import com.bosonit.training.security.persona.entity.Persona;
import com.bosonit.training.security.persona.infrastructure.repository.PersonaDao;
import com.bosonit.training.security.role.entity.Role;
import com.bosonit.training.security.role.infrastructure.dtos.RoleINputDto;
import com.bosonit.training.security.role.infrastructure.dtos.RoleOUTputDto;
import com.bosonit.training.security.role.infrastructure.repository.RepositoryRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceImplRole implements ServiceRole {

    @Autowired
    RepositoryRole repositoryRole;
    @Autowired
    PersonaDao personaDao;



    @Override
    public RoleOUTputDto loadRole(RoleINputDto roleINputDto){
        Role role= roleINputDto.transformIntoRole();
        repositoryRole.save(role);
        return new RoleOUTputDto(role);
    }

    @Override
    public void addRolePersona(String name, String nameRole) {
        log.info("adding role {} to persona {}",nameRole,name);
        Role roles= repositoryRole.findByNameRole(nameRole);
        if (roles==null)
            throw new EntityNotFoundException("The role with name: "+nameRole+" does not exist in the database", HttpStatus.FOUND,new Date());


    }

    @Override
    public List<RoleOUTputDto> viewRole(String nameRole) {
        List<Role> role= Collections.singletonList(repositoryRole.findByNameRole(nameRole));
        if (role.isEmpty())
            throw new EntityNotFoundException("The role with name: "+nameRole+" does not exist in the database", HttpStatus.FOUND,new Date());

        return role.stream().map(RoleOUTputDto::new).collect(Collectors.toList());
    }

    @Override
    public List<RoleOUTputDto> viewall() {
        List<RoleOUTputDto> listaRole=new ArrayList<>();
        repositoryRole.findAll().stream().map(RoleOUTputDto::new).forEach(personOutputDto -> listaRole.add(personOutputDto));
        return listaRole;
    }

    @Override
    public void delete(Integer id) throws Exception {

        Role role= repositoryRole.findById(id).orElseThrow(()-> {throw new EntityNotFoundException("The person with Id: "+id+" does not exist in the database", HttpStatus.FOUND,new Date());});
        repositoryRole.delete(role);
    }
    @Override
    public void addRoleToPerson(String username, String roleName) {

    }

}
