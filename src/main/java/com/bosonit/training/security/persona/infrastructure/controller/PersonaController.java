package com.bosonit.training.security.persona.infrastructure.controller;

import com.bosonit.training.security.persona.entity.Persona;
import com.bosonit.training.security.persona.service.ServicePersona;
import com.bosonit.training.security.role.entity.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
//@RequestMapping("api")
@RequiredArgsConstructor
public class PersonaController {

    @Autowired
    ServicePersona servicepersona;


    @PostMapping("/addperson")
    public ResponseEntity<Persona> guardarpersona(@RequestBody Persona persona) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/addperson").toUriString());
        return ResponseEntity.created(uri).body(servicepersona.savePersona(persona));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Persona>> viewall(){
        return ResponseEntity.ok().body(servicepersona.viewall());
    }

    @PostMapping("/addrole")
    public ResponseEntity<Role> guardarroles(@RequestBody Role role) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/persona/addrole").toUriString());
        return ResponseEntity.created(uri).body(servicepersona.saveRole(role));
    }
    @PostMapping("/addrole/person")
    public ResponseEntity<?> addRoleToPerson(@RequestBody RoleToPerson roleToPerson) throws Exception {
        servicepersona.addRoleToUser(roleToPerson.getUsername(),roleToPerson.getRolename());
        return ResponseEntity.ok().build();
    }
    @Data
    class RoleToPerson{
        private String username;
        private String rolename;
    }



}

