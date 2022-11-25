package com.bosonit.training.security.persona.service;

import com.bosonit.training.security.Exception.EntityNotFoundException;

import com.bosonit.training.security.persona.entity.Persona;

import com.bosonit.training.security.persona.infrastructure.repository.PersonaDao;
import com.bosonit.training.security.role.entity.Role;

import com.bosonit.training.security.role.infrastructure.repository.RepositoryRole;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@Slf4j
public class ServiceImplPersona implements ServicePersona, UserDetailsService {


    @Autowired
    PersonaDao personaDao;

   @Autowired
    RepositoryRole repositoryRole;
//    @Autowired
//    PasswordEncoder passwordEncoder;



    @Override
    public List<Persona> viewall() {
       return personaDao.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona= personaDao.findByUsername(username);
        if(persona==null) {
            log.error("persona no se encuentra en la base de datos");
            throw new EntityNotFoundException("Persona username " + username + "no se encuentra en la base de datos", HttpStatus.FOUND, new Date());
        }else {
            log.info("persona en la base de datos");
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        persona.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getNameRole())));
        return new org.springframework.security.core.userdetails.User(persona.getUsername(),persona.getPassword(),authorities);
    }


    @Override
    public Persona savePersona(Persona persona) throws Exception {
        log.info("persona {} guardada en la base de datos",persona.getName());
//        persona.setPassword(passwordEncoder.encode(persona.getPassword()));

        return personaDao.save(persona);

    }

    @Override
    public Role saveRole(Role role) {
        log.info("role {} guardado en la base de datos",role.getNameRole());
        return repositoryRole.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("role {} asignado a la persona {}",roleName,username);
        Persona persona= personaDao.findByUsername(username);
        Role role= repositoryRole.findByNameRole(roleName);
        persona.getRoles().add(role);

    }

    @Override
    public Persona getPersona(String username) {
        log.info("todos las personas");
        return personaDao.findByUsername(username);
    }


}


