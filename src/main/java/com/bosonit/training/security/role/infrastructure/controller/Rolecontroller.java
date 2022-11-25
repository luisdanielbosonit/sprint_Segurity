package com.bosonit.training.security.role.infrastructure.controller;

import com.bosonit.training.security.persona.service.ServicePersona;
import com.bosonit.training.security.role.infrastructure.dtos.RoleINputDto;
import com.bosonit.training.security.role.infrastructure.dtos.RoleOUTputDto;

import com.bosonit.training.security.role.service.ServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
@RequestMapping("/role")
@RestController
public class Rolecontroller {

    @Autowired
    ServiceRole serviceRole;

    @Autowired
    ServicePersona servicePersona;


    @PostMapping("/addrole")
    public ResponseEntity<RoleOUTputDto> guardarRole(@RequestBody RoleINputDto roleINputDto) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/addrole").toUriString());
//        return ResponseEntity.created(uri).body(servicePersona.loadRole(roleINputDto));
        return null;
    }

//    @GetMapping("get/{id}")
//    public RoleOUTputDto show(@PathVariable Integer id) throws Exception {
//
//        return serviceRole.viewId(id);
//
//    }
    @GetMapping("/view/{nameRole}")
    public List<RoleOUTputDto> getRoleByUsername(@PathVariable String nameRole) throws Exception {
        return serviceRole.viewRole(nameRole);
    }


//    @PutMapping("/put/{id}")
//    public RoleOUTputDto modificarRole(@PathVariable Integer id, @RequestBody RoleINputDto roleINputDto) throws Exception{
//
//        return serviceRole.update(id,roleINputDto);
//    }
    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable Integer id) throws Exception {
        serviceRole.delete(id);
        }

    @GetMapping("/getall")
    public List<RoleOUTputDto> viewall(){
        return (List<RoleOUTputDto>) serviceRole.viewall();
    }


}

